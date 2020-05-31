<template>
  <div>
    <ul>
      <li v-for="file in mediaList" :key="file.id">
        <b-card>
          <input type="checkbox" :value="file.id" v-model="selected" />
          {{file.fileName}}
          <img
            v-if="file.mimeType.startsWith('image/')"
            :src="images[file.id]"
            width="200"
          />
          <video
            v-if="file.mimeType.startsWith('video/')"
            :src="images[file.id]"
            width="200"
            controls
          />
          <b-button variant="danger" @click="deleteMedia(file.id)">Delete</b-button>

          <div v-if="statusList[file.id] != undefined">Status: {{statusList[file.id].status}}</div>
        </b-card>
      </li>
    </ul>
  </div>
</template>

<script>
import Media from "../../model/media/Media";
export default {
  props: {
    mediaList: Array,
    selectable: {
      type: Boolean,
      default: false
    },
    initialSelection: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      images: {},
      selected: [],
      statusList: {}
    };
  },
  methods: {
    async downloadMedia(file) {
      const response = await Media.downloadMedia(file);
      this.$set(this.images, file.id, response);
    },
    mapToObjs(idList) {
      return idList
        .map(id => this.findMedia(id))
        .filter(media => media != null);
    },
    findMedia(id) {
      return this.mediaList.find(img => img.id == id);
    },
    deleteMedia(id) {
      this.$emit("deleted", this.findMedia(id));
    },
    watchStatus(status) {
      let mediaId = status.mediaId;
      if (!status.done) {
        setTimeout(async () => {
          let status = await Media.getStatus(mediaId);
          this.statusList[mediaId] = status;
          this.$set(this.statusList, mediaId, status);
          this.watchStatus(status);
        }, 500);
      } else {
        let media = this.findMedia(mediaId);
        this.downloadMedia(media);
      }
    }
  },
  watch: {
    initialSelection(newValue) {
      this.selected = this.initialSelection.map(media =>
        typeof media == "object" ? media.id : media
      );
    },
    selected(newValue) {
      this.$emit("selected", this.mapToObjs(newValue));
    },
    mediaList(newValue) {
      newValue.forEach(file => this.downloadMedia(file));
      newValue.forEach(file => {
        Media.getStatus(file.id).then(status => {
          this.$set(this.statusList, file.id, status);
          this.watchStatus(status);
        });
      });
    }
  },
  created() {
    this.mediaList.forEach(file => this.downloadMedia(file));
  }
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}
</style>