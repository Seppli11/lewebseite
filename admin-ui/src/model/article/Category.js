import Article from "@/model/article/Article";

/**
 * The category model class
 */
export default class Category {
    /**
     * 
     * @param {number} id the primary key
     * @param {String} name the name of the category
     * @param {Array<Article>} articles the articles in this category
     */
    constructor(id, name, articles = []) {
        this.id = id;
        this.name = name;
        this.articles = articles
    }

    static fromObj(obj) {
        function mapArticles(array) {
            if (array == null) return null;
            return array.map(Article.fromObj);
        }
        if (obj == null) return null;
        return new Category(obj.id, obj.name, mapArticles(obj.articles));
    }
}