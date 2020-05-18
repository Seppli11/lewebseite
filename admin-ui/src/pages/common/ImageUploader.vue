<template>
  <div class="m-2">
    <b-form-file v-model="files" placeholder="Choose image" accept="image/*"></b-form-file>
    <b-button class="mt-2" variant="primary" @click="upload">Upload</b-button>
    <h2>Images</h2>
    <image-list
      :mediaList="mediaList"
      :initialSelection="initialSelection"
      @selected="imgs => $emit('selected', imgs)"
    />
  </div>
</template>

<script>
import Media from "../../model/media/Media";
import ImageListVue from "./ImageList.vue";
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
      mediaList: []
    };
  },
  methods: {
    async upload() {
      await Media.uploadMedia(this.files);
      this.mediaList = await Media.getAllMedia();
    }
  },
  async created() {
    this.mediaList = await Media.getAllMedia();
  },
  components: {
    ImageList: ImageListVue
  }
};
</script>

<style scoped>
</style>