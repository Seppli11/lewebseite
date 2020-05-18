An item in the article list

<template>
  <b-card class="article-list-item-container" :title="article.title">
    <b-card-text>{{article.shortText}}</b-card-text>
    <b-button-toolbar>
      <b-button-group>
        <b-button variant="primary" @click="editArticle">
          <b-icon-pencil class="mr-2" />Edit
        </b-button>
        <b-button variant="danger" @click="deleteArticle">
          <b-icon-trash class="mr-2" />Delete
        </b-button>
      </b-button-group>
    </b-button-toolbar>
  </b-card>
</template>

<script>
import Article from "../../../model/article/Article";
export default {
  props: {
    article: Article
  },
  methods: {
    deleteArticle() {
      this.$emit("delete", this.article);
    },
    editArticle() {
      this.$emit("edit", this.article);
    }
  }
};
</script>

<style scoped>
</style>