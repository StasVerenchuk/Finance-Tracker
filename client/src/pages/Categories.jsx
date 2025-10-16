import React, { useEffect, useState } from 'react';
import { getMyCategories, addCategory, deleteCategory } from '../api/category';

import './styles/Categories.css'; // окремий файл стилів

const Categories = () => {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [newCategory, setNewCategory] = useState({
    name: '',
    colorHex: '#74b9ff',
    type: 'EXPENSE',
  });

  const userId = localStorage.getItem('userId'); // збережений після логіну

  useEffect(() => {
    if (!userId) {
      setError('Користувач не авторизований');
      setLoading(false);
      return;
    }

    const fetchData = async () => {
      try {
        const data = await getMyCategories(userId);
        setCategories(data);
      } catch (err) {
        setError('Помилка при завантаженні категорій');
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [userId]);
  
  const handleAdd = async (e) => {
    e.preventDefault();
    try {
      const categoryToAdd = { ...newCategory, userId: Number(userId) };
      const created = await addCategory(categoryToAdd);
      setCategories([...categories, created]);
      setNewCategory({name: '', colorHex: '#74b9ff', type: 'EXPENSE'});
    } catch (err) {
      setError('Не вдалося додати категорію');
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteCategory(id, userId);
      setCategories(categories.filter((cat) => cat.id !== id));
    } catch {
      setError('Помилка при видаленні категорії');
    }
  };

  if (loading) return <p>Завантаження...</p>;
  if (error) return <p style={{ color: 'red' }}>{error}</p>;
  
  return (
    <div className="container-categories">
      <div className="categories-card">
        <h2 className="categories-title">Мої категорії</h2>

        {/* Форма додавання */}
        <form className="add-category-form" onSubmit={handleAdd}>
          <input
            type="text"
            placeholder="Назва категорії"
            value={newCategory.name}
            onChange={(e) =>
              setNewCategory({ ...newCategory, name: e.target.value })
            }
            required
          />

          <select
            value={newCategory.type}
            onChange={(e) =>
              setNewCategory({ ...newCategory, type: e.target.value })
            }
          >
            <option value="INCOME">Дохід</option>
            <option value="EXPENSE">Витрата</option>
          </select>

          <input
            type="color"
            value={newCategory.colorHex}
            onChange={(e) =>
              setNewCategory({ ...newCategory, colorHex: e.target.value })
            }
          />

          <button type="submit">Додати</button>
        </form>

        {/* Список категорій */}
        <div className="categories-list">
          {categories.length === 0 ? (
            <p>Немає категорій</p>
          ) : (
            categories.map((cat) => (
              <div
                key={cat.id}
                className="category-item"
                style={{ borderLeft: `10px solid ${cat.colorHex}` }}
              >
                <span>
                  {cat.name} ({cat.type === 'INCOME' ? 'Дохід' : 'Витрата'})
                </span>
                <button
                  className="delete-btn"
                  onClick={() => handleDelete(cat.id)}
                >
                  ×
                </button>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
};

export default Categories;
