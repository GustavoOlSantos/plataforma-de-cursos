// services/jwt.js
import { jwtDecode } from "jwt-decode";

export function getToken() {
    const rawToken = localStorage.getItem("token");

    if (!rawToken || rawToken === "undefined") return null;

    try {
        return jwtDecode(rawToken);
    } catch (e) {
        return null;
    }
}