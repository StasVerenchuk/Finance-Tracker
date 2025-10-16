import React, { useState } from 'react';
import { login } from '../api/auth';
import { useNavigate } from 'react-router-dom';
import './styles/Login.css';

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = await login(email, password);
      localStorage.setItem('token', data.accessToken);
      navigate('/');
    } catch (err) {
      setError('Invalid email or password');
    }
  };

  return (
    <div className="container-login100">
      <div className="wrap-login100 p-t-50 p-b-90">
        <form className="login100-form validate-form flex-sb flex-w" onSubmit={handleSubmit}>
          <span className="login100-form-title p-b-51">Login</span>

          {error && <p style={{ color: "red" }}>{error}</p>}

          <div className="wrap-input100 validate-input m-b-16" data-validate="Email is required">
            <input
              className="input100"
              type="email"
              name="email"
              placeholder="Email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
            <span className="focus-input100"></span>
          </div>

          <div className="wrap-input100 validate-input m-b-16" data-validate="Password is required">
            <input
              className="input100"
              type="password"
              name="pass"
              placeholder="Password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
            <span className="focus-input100"></span>
          </div>

          <div className="flex-sb-m w-full p-t-3 p-b-24">
            <div>
              <p onClick={() => navigate('/register')}>
                No account? <a className="txt1">Register here</a>
              </p>
            </div>
          </div>

          <div className="container-login100-form-btn m-t-17">
            <button className="login100-form-btn" type="submit">Login</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
