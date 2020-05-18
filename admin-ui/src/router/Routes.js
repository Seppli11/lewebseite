export default new class Routes {
    constructor() {
    }

    _init(router) {
        this.router = router;
    }

    editArticle(id) {
        this.router.push(`/articles/${id << 0}/edit`)
    }

    addArticle() {
        this.router.push(`/articles/edit`)
    }
}