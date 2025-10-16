import React, { useState } from 'react';
import { register } from '../api/auth';
import { useNavigate } from 'react-router-dom';

import './styles/Register.css'

const Register = () => {
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [currency, setCurrency] = useState('UAH');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const data = await register(email, name, password, currency);
            localStorage.setItem('token', data.accessToken);
            navigate('/login');
        } catch (err) {
            setError('Registration failed');
        }
    };

    return (
        <div className="container-login100">
            <div className="wrap-login100 p-t-50 p-b-90">
                <form className="login100-form validate-form flex-sb flex-w" onSubmit={handleSubmit}>
                    <span className="login100-form-title p-b-51">
                        Register
                    </span>

                    {error && (
                        <div className="alert alert-danger w-100 text-center m-b-16">
                            {error}
                        </div>
                    )}

                    <div className="wrap-input100 validate-input m-b-16" data-validate="Email is required">
                        <input
                            className="input100"
                            type="email"
                            name="email"
                            placeholder="Email"
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                            required
                        />
                        <span className="focus-input100"></span>
                    </div>

                    <div className="wrap-input100 validate-input m-b-16" data-validate="Name is required">
                        <input
                            className="input100"
                            type="text"
                            name="name"
                            placeholder="Name"
                            value={name}
                            onChange={e => setName(e.target.value)}
                            required
                        />
                        <span className="focus-input100"></span>
                    </div>

                    <div className="wrap-input100 validate-input m-b-16" data-validate="Password is required">
                        <input
                            className="input100"
                            type="password"
                            name="password"
                            placeholder="Password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                            required
                        />
                        <span className="focus-input100"></span>
                    </div>

                    <div className="wrap-input100 m-b-16">
                        <select
                            className="input100"
                            value={currency}
                            onChange={e => setCurrency(e.target.value)}
                        >
                            <option value="UAH">UAH</option>
                            <option value="USD">USD</option>
                            <option value="EUR">EUR</option>
                        </select>
                        <span className="focus-input100"></span>
                    </div>

                    <div className="flex-sb-m w-full p-t-3 p-b-24">
            
                    <div>
                        <p onClick={() => navigate('/login')}>
                            <a className="txt1">
                            Return to login
                        </a>
                        </p>
                        
                        </div>
                    </div>

                    <div className="container-login100-form-btn m-t-17">
                        <button className="login100-form-btn">
                            Register
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Register;