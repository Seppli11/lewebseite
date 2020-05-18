<template>
  <div>
    <h1 v-if="articleId >= 0">Edit Article</h1>
    <h1 v-else>Add Article</h1>

    <b-form class="m-4">
      <b-form-group
        id="title-group"
        label="Title"
        label-for="title-input"
        description="The title of the article"
      >
        <b-form-input id="title-input" v-model="title" type="text"></b-form-input>
      </b-form-group>
      <b-button @click="save" variant="primary">Save Article</b-button>
    </b-form>

    <b-tabs content-class="mt-3" v-model="tabIndex">
      <b-tab title="Edit Article" active>
        <vue-simplemde v-model="text" />
      </b-tab>
      <b-tab title="Header Images">
        <image-uploader
          :initialSelection="initialSelection"
          @selected="selected => selectedHeaderImages = selected"
        />
        <b-button variant="primary" @click="saveHeaderImages">Save Header Images</b-button>
      </b-tab>
    </b-tabs>
  </div>
</template>

<script>
import VueSimplemde from "vue-simplemde";
import "simplemde/dist/simplemde.min.css";

import ImageUploader from "@/pages/common/ImageUploader";

import Article from "../../model/article/Article";

export default {
  data() {
    return {
      updateMode: false,
      articleId: this.$route.params.id,
      title: "",
      text: "",

      tabIndex: 0,

      selectedHeaderImages: [],
      initialSelection: []
    };
  },
  async created() {
    if (this.articleId >= 0) {
      const article = await Article.getArticle(this.articleId);
      this.title = article.title;
      this.selectedHeaderImages = article.headerImages;
      this.initialSelection = article.headerImages;
      this.text = await Article.getText(this.articleId);
      this.updateMode = true;
    }
  },
  methods: {
    async save() {
      if (this.updateMode) {
        Article.updateArticle({ id: this.articleId, title: this.title });
        Article.setText(this.articleId, this.text);
      } else {
        const createdArticle = await Article.createArticle({
          title: this.title
        });
        Article.setText(createdArticle.id, this.text);
        this.$router.back();
      }
    },
    async saveHeaderImages() {
      await Article.setHeaderImages(
        { id: this.articleId },
        this.selectedHeaderImages
      );
      this.tabIndex = 0;
    }
  },
  components: {
    VueSimplemde,
    ImageUploader
  }
};
</script>

<style scoped>
</style>