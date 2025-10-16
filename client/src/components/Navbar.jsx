import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './styles/Navbar.css';

const Navbar = () => {
    const navigate = useNavigate();
    const isAuthenticated = !!localStorage.getItem('token');

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };

    return (
        <nav className="navbar100">
            <div className="container-navbar100">
                <h1 className="navbar-logo" onClick={() => navigate('/')}>
                    Finance Tracker
                </h1>
                <div className="navbar-links">
                    {isAuthenticated && (
                        <>
                            <Link to="/categories" className="navbar-btn">Категорії</Link>
                        </>
                    )}
                    {!isAuthenticated ? (
                        <>
                            <Link to="/login" className="navbar-btn">
                                Увійти
                            </Link>
                            <Link to="/register" className="navbar-btn">
                                Реєстрація
                            </Link>
                        </>
                    ) : (
                        <button className="navbar-btn logout" onClick={handleLogout}>
                            Вихід
                        </button>
                    )}
                </div>
            </div>
        </nav>
    );
};

export default Navbar;

