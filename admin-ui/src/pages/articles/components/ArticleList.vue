<template>
  <ul>
    <li v-for="article in articles" :key="article.id">
      <ArticleListItem @delete="deleteArticle" @edit="editArticle" :article="article" />
    </li>
  </ul>
</template>

<script>
import ArticleListItem from "./ArticleListItem.vue";

export default {
  props: {
    articles: Array
  },
  methods: {
    deleteArticle(article) {
      this.$emit("delete", article);
    },
    editArticle(article) {
      this.$emit("edit", article);
    }
  },
  components: {
    ArticleListItem
  }
};
</script>

<style scoped>
ul {
  list-style-type: none;
  margin: 5px;
  padding: 0;
}
</style>