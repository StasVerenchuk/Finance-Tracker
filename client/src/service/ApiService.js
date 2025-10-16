import axios from 'axios';
import CryptoJS, { enc } from 'crypto-js';

export default class ApiService {
    static BASE_URL = 'http://localhost:8080/api';
    static ENCRYPTION_KEY = 'finance-tracker-encryption';

    // encrypt data using cryptojs
    static encrypt(data){
        return CryptoJS.AES.encrypt(data, this.ENCRYPTION_KEY).toString();
    }

    // decrypt data using cryptojs
    static decrypt(data){
        const bytes = CryptoJS.AES.decrypt(data, this.ENCRYPTION_KEY);
        return bytes.toString(CryptoJS.enc.Utf8);
    }

    // save token with encryption
    static saveToken(token){
        const encryptedToken = this.encrypt(token);
        localStorage.setItem('token', encryptedToken);
    }

    // retrieve token with encryption
    static getToken(){
        const encryptedToken = localStorage.getItem('token');
        if (!encryptedToken) return null;
        return this.decrypt(encryptedToken);
    }

    // clear token
    static clearAuth(){
        localStorage.removeItem('token');
    }


    static getHeader(){
        const token = this.getToken();
        return {
            Authorization:  `Bearer ${token}`,
            'Content-Type': "application/json",
        };
    }

    /**Auth api 
    static async login(email, password){
        const response = await axios.post(API_URL + 'login', {email, password});
        return response.data;
    }

    static async register(email, name, password, currency){
        const response = await axios.post(API_URL + 'register', {email, name, password, currency});
        return response.data;
    }
    */

    static async get(url){
        const response = await axios.get(this.BASE_URL + url, {headers: this.getHeader()});
        return response.data;
    }

    static async post(url, data){
        const response = await axios.post(this.BASE_URL +url, data, {headers: this.getHeader()});
        return response.data;
    }

    static async put(url, data){
        const response = await axios.put(this.BASE_URL + url, data, {headers: this.getHeader()});
        return response.data;
    }

    static async delete(url){
        const response = await axios.delete(this.BASE_URL + url, {headers: this.getHeader()});
        return response.data;
    }
}