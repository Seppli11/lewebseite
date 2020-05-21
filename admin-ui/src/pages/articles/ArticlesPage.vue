<template>
  <div class="container-fluid">
    <h1>Articles</h1>
    <div id="article-list-toolbar">
      <b-button-toolbar>
        <b-button variant="primary" @click="addArticle">
          <b-icon-plus-circle class="mr-2" />New Article
        </b-button>
      </b-button-toolbar>
    </div>
    <ArticleList :articles="articles" @delete="deleteArticle" @edit="editArticle" />
  </div>
</template>

<script>
import ArticleList from "./components/ArticleList.vue";
import Article from "../../model/article/Article";
import Routes from "../../router/Routes";
export default {
  data() {
    return {
      articles: []
    };
  },
  methods: {
    async deleteArticle(article) {
      await Article.deleteArticle(article);
      this.articles = this.articles.filter(art => art.id != article.id);
    },
    editArticle(article) {
      Routes.editArticle(article.id);
    },
    addArticle() {
      Routes.addArticle();
    }
  },
  components: {
    ArticleList
  },
  async beforeCreate() {
    this.articles = await Article.getArticles();
  }
};
</script>

<style scoped>
#article-list-toolbar {
  margin: 5px;
}
</style>