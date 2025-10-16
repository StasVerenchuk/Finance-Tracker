import axios from 'axios';

const API_URL = 'http://localhost:8080/api/category';

// Додавання токеку до кожного запиту
const getAuthHeader = () => {
    const token = localStorage.getItem('token');
    return token ? {Authorization: `Bearer ${token}`} : {};
};

// Отримати усі категорії користувача
export const getMyCategories = async (userId) => {
    const response = await axios.get(`${API_URL}/my-categories`, {headers: getAuthHeader(), params: {userId}});
    return response.data;
};

// Додати нову категорію
export const addCategory = async (category) => {
    const response = await axios.post(`${API_URL}/add`, category, {headers: getAuthHeader()});
    return response.data;
};

// Оновити категорію
export const updateCategory = async (id, userId, category) => {
    const response = await axios.put(`${API_URL}/update/${id}`, category, {headers: getAuthHeader(), params: {userId}});
    return response.data;
};

// Видалити категорію
export const deleteCategory = async (id, userId) => {
    await axios.delete(`${API_URL}/delete/${id}`, {headers: getAuthHeader(), params: {userId}});
};