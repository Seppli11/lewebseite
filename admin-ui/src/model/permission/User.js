export default class User {
    constructor(id, username, email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    static fromObj(obj) {
        if (obj == null) return null;
        return new User(obj.id, obj.username, obj.email);
    }
}