import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import Login from './pages/Login';
import Register from './pages/Register';
import Categories from './pages/Categories';

import './components/styles/Navbar.css';
import './pages/styles/Login.css';
import './App.css'

// 👇 Головна сторінка — доступна всім
const HomePublic = () => (
  <div className="container-login100">
    <div className="wrap-login100">
      <h2 className="login100-form-title">Ласкаво просимо до Finance Tracker!</h2>
      <p>
        Персональний трекер фінансів — це сучасний веб-додаток, який допомагає користувачам
        вести облік власних доходів та витрат, планувати бюджет і контролювати фінансові потоки.
      </p>
      <p>Основні можливості проєкту:</p>
      <ul style={{ marginBottom: '20px' }}>
        <li>Додавання та категоризація доходів і витрат.</li>
        <li>Візуалізація фінансових даних за допомогою графіків та діаграм.</li>
        <li>Планування бюджету та контроль витрат у реальному часі.</li>
        <li>Інтерактивні звіти за вибраний період.</li>
        <li>Безпечне зберігання персональних фінансових даних.</li>
      </ul>
      <p>
        Завдяки Finance Tracker ви зможете краще розуміти свої фінансові звички та ефективно планувати
        майбутні витрати, приймаючи обґрунтовані рішення щодо особистого бюджету.
      </p>
    </div>
    
  </div>
);

const HomePrivate = () => (
  <div className="container-login100">
    <div className="wrap-login100">
      <h2 className="login100-form-title">Ваш особистий кабінет</h2>
      <p>
        Тут буде відображатися персональна інформація про доходи та витрати.
      </p>
    </div>
  </div> 
);

const App = () => {
  const isAuthenticated = !!localStorage.getItem('token');

  return (
    <Router>
      <Navbar />
      <div className="main-content" style={{ width: '100%', paddingTop: '70px' }}>
        <Routes>
          <Route path="/" element={isAuthenticated ? <HomePrivate /> : <HomePublic />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/categories" element={<Categories />} />
          <Route path="*" element={<Navigate to="/" />} />
        </Routes>
      </div>

      <footer style={{ textAlign: 'center', padding: '20px', marginTop: '20px', color: '#333' }}>
        &copy; 2025 Finance Tracker.
      </footer>
    </Router>
  );
};

// Всі права захищено.

export default App;
