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
      selected: []
    };
  },
  methods: {
    async getUrl(file) {
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
    }
  },
  watch: {
    initialSelection(newValue) {
      this.selected = this.initialSelection.map(media => media.id);
    },
    selected(newValue) {
      this.$emit("selected", this.mapToObjs(newValue));
    },
    mediaList(newValue) {
      newValue.forEach(file => this.getUrl(file));
    }
  },
  created() {
    this.mediaList.forEach(file => this.getUrl(file));
  }
};
</script>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}
</style>