import { getFetch, uploadFile, simpleFetch, deleteFetch } from "@/model/rest/RestUtils";

export default class Media {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }

    static getSupportedMimeTypes() {
        return getFetch("/media/supportedMimeTypes")
    }

    static getMedia(id) {
        return getFetch(`/media/${id}`);
    }

    static getAllMedia() {
        return getFetch(`/media`)
    }

    static deleteMedia(id) {
        return deleteFetch(`/media/${id}`);
    }

    static getStatus(id) {
        return getFetch(`/media/${id}/status`);
    }

    static uploadMedia(files) {
        return uploadFile(`/media`, files)
    }

    static downloadMedia(file) {
        return simpleFetch(`/media/${file.id}/file`, "GET")
            .then(response => response.blob())
            .then(blob => new Promise((resolve, reject) => {
                const reader = new FileReader();
                reader.onloadend = () => resolve(reader.result);
                reader.onerror = reject
                reader.readAsDataURL(blob);
            }));
    }
}