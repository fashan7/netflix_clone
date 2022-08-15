import "./login.scss";
import { NavLink } from "react-router-dom";
import { postLoginData } from "../../services/login.services";
import { useEffect, useState } from "react";

export default function Login() {
  const [getToken, SetToken] = useState();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  var tokenAccess = sessionStorage.getItem("sessionToken");
  var usernameStore = sessionStorage.getItem("username");
  const value = {password, username}
  const handleSubmit = async (e) => {
    e.preventDefault();
    let token = await postLoginData(value)
    if (token != 404){ 
      sessionStorage.setItem("sessionToken", token);
      sessionStorage.setItem("username", username);
      SetToken(tokenAccess)
      window.location.href="/"
    } else {
      alert("Oops: Credentials are wrong")
    }
  };

  console.log(getToken)
  
  return (
    <div className="login">
      <div className="top">
        <div className="wrapper">
          <img
            className="logo"
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Netflix_2015_logo.svg/2560px-Netflix_2015_logo.svg.png"
            alt=""
          />
        </div>
      </div>
      <div className="container">
        <form onSubmit={handleSubmit}>
          <h1>Sign In</h1>
          <input type="text" placeholder="Username" onChange={(e) => setUsername(e.target.value)}/>
          <input type="password" placeholder="Password" onChange={(e) => setPassword(e.target.value)}/>
          <button className="loginButton">Sign In</button>
          <span>
            New to Netflix? <NavLink to="/register"><b>Sign up now.</b></NavLink>
          </span>
          <small>
            This page is protected by Google reCAPTCHA to ensure you're not a
            bot. <b>Learn more</b>.
          </small>
        </form>
      </div>
    </div>
  );
}
