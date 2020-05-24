<template>
  <div class="m-2">
    <b-form-file v-model="files" placeholder="Choose image" :accept="supportedMediaTypes" multiple></b-form-file>
    <b-spinner variant="primary" label="Spinning" v-if="uploading"></b-spinner>
    <b-button class="mt-2" variant="primary" @click="upload">Upload</b-button>
    <h2>Images</h2>
    <media-list
      :mediaList="mediaList"
      :initialSelection="initialSelection"
      @selected="imgs => $emit('selected', imgs)"
    />
  </div>
</template>

<script>
import Media from "../../model/media/Media";
import MediaListVue from "./MediaList.vue";
export default {
  props: {
    initialSelection: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      files: [],
      mediaList: [],
      supportedMediaTypes: "",
      uploading: false
    };
  },
  methods: {
    async upload() {
      this.uploading = true;
      try {
        await Media.uploadMedia(this.files);
      } finally {
        this.uploading = false;
        this.files = [];
      }
      this.mediaList = await Media.getAllMedia();
    }
  },
  async created() {
    this.mediaList = await Media.getAllMedia();
    this.supportedMediaTypes = (await Media.getSupportedMimeTypes()).join(", ");
  },
  components: {
    MediaList: MediaListVue
  }
};
</script>

<style scoped>
</style>