import { getFetch } from "@/model/rest/RestUtils";
import User from "@/model/permission/User";
import Category from "@/model/article/Category";
import { deleteFetch, postFetch, putFetch } from "@/model/rest/RestUtils";

export default class Article {
    constructor(id, title, author, shortContent, categories = [], headerImages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.shortContent = shortContent;
        this.categories = categories;
        this.headerImages = headerImages;
    }

    static fromObj(obj) {
        if (obj == null) return null;
        return new Article(obj.id, obj.title, User.fromObj(obj.author), obj.shortContent, Category.fromObj(obj.categories), obj.headerImages);
    }

    static async getArticles() {
        let articles = await getFetch(this.ARTICLES_URL)
        return articles.map(this.fromObj)
    }

    static async getArticle(id) {
        const articleObj = await getFetch(this.ARTICLES_URL + "/" + id)
        return this.fromObj(articleObj);
    }

    static async getText(id) {
        return await getFetch(this.ARTICLES_URL + "/" + id + "/text")
    }

    static async setText(id, text) {
        await putFetch(this.ARTICLES_URL + "/" + id + "/text", text, false)
    }

    static async deleteArticle(article) {
        await deleteFetch(this.ARTICLES_URL + "/" + article.id);
    }

    static async updateArticle(article) {
        return await putFetch(this.ARTICLES_URL + "/" + article.id, article);
    }

    static async createArticle(article) {
        return await postFetch(this.ARTICLES_URL, article);
    }

    static async addHeaderImages(article, images = []) {
        await Promise.all(images.map(async (img) => {
            this.addHeaderImage(article, img);
        }))
    }

    static async addHeaderImage(article, media) {
        putFetch(this.ARTICLES_URL + "/" + article.id + "/headerImage/" + media.id)
    }

    static async setHeaderImages(article, images) {
        const idArray = images.map(img => img.id);
        putFetch(this.ARTICLES_URL + "/" + article.id + "/setHeaderImage", idArray, true);
    }

    static get ARTICLES_URL() {
        return "/articles"
    }
}