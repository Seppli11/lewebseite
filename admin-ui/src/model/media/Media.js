import { getFetch, uploadFile, simpleFetch } from "@/model/rest/RestUtils";

export default class Media {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }

    static getMedia(id) {
        return getFetch(`/media/${id}`);
    }

    static getAllMedia() {
        return getFetch(`/media`)
    }

    static uploadMedia(files) {
        return uploadFile(`/media`, files)
    }

    static downloadMedia(file) {
        console.log(file)
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