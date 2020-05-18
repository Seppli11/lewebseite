export const BASE_URL = "/api";

export async function getFetch(url) {
    return genericFetch(url)
}

export async function postFetch(url, body = null, json = true) {
    return genericFetch(url, "POST", body, json);
}

export async function putFetch(url, body = null, json = true) {
    return genericFetch(url, "PUT", body, json);
}

export async function deleteFetch(url) {
    return genericFetch(url, "DELETE");
}

export async function uploadFile(url, files) {
    const formData = new FormData();
    if (typeof (files) == "array") {
        files.forEach(file => formData.append("files", file))
    } else {
        formData.append("files", files)
    }

    simpleFetch(url, "POST", formData, null)
}

async function genericFetch(url, method = "GET", body = null, json = true) {
    try {
        let response = null;
        if (body != null && json == true) {
            response = simpleFetch(url, method, JSON.stringify(body), "application/json");
        } else if (body != null) {
            response = simpleFetch(url, method, body, "text/plain")
        } else {
            response = simpleFetch(url, method, null, null);
        }
        response = await response;
        const contentTypeHeader = response.headers.get("Content-Type");
        if (contentTypeHeader != null && contentTypeHeader.indexOf("application/json") != -1) {
            return response.json();
        }
        return response.text();
    } catch (e) {
        throw new RestError(url, null, `Couldn't fetch "${url}" because the request failed (${e})`)
    }
}

export async function simpleFetch(url, method, body, contentType) {
    try {
        const options = {
            method,
            mode: "cors",
            cache: "no-cache",
            credentials: "same-origin",
            headers: {
                "Authorization": "Bearer " + LoginManager.getToken()
            },
            redirect: "follow",
            referrerPolicy: "no-referrer",
        };
        if (body != null) {
            options.body = body;
            if (contentType != null) {
                options.headers["Content-Type"] = contentType;
            }
        }
        const response = await fetch(BASE_URL + url, options)

        // recieved forbidden -> not logged in
        if (response.status == 403) {
            window.location.hash = "/login";
            throw new LoginError(url, response, "Not logged in");
        }

        if (!response.ok) {
            throw new RestError(url, response, `Couldn't fetch "${url}" because of ${response.statusText} (${response.status})`)
        }
        return response;
    } catch (e) {
        throw new RestError(url, null, `Couldn't fetch "${url}" because the request failed (${e})`)
    }
}

const LOGIN_URL = "/login";
const TOKEN_HEADER = "Authorization";

export async function login(username, password) {
    const response = await fetch(BASE_URL + LOGIN_URL, {
        method: "POST",
        mode: "cors",
        cache: "no-cache",
        credentials: "same-origin",
        headers: {
            "Content-Type": "application/json"
        },
        redirect: "follow",
        referrerPolicy: "no-referrer",
        body: JSON.stringify({
            username: username,
            password: password
        })
    })

    if (!response.ok) {
        throw new RestError(url, response, `Couldn't login because of ${response.statusText} (${response.status})`)
    };

    const token = response.headers.get(TOKEN_HEADER);
    if (token == null) {
        throw new RestError(url, response, `Recieved token is null`)
    }

    LoginManager.setToken(token)

}

export const LoginManager = new class LoginManager {
    constructor() {
        this.token = localStorage.getItem("token");
    }
    setToken(token) {
        this.token = token;
        localStorage.setItem("token", token);
    }

    getToken() {
        return this.token;
    }
}

export class RestError extends Error {
    constructor(url, response, msg) {
        super(msg)
        this.url = url;
        this.response = response;
    }
}

export class LoginError extends RestError {
    constructor(url, response, msg) {
        super(url, response, msg)
    }
}