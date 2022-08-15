import { useRef } from "react";
import { useState } from "react";
import "./register.scss";
import { NavLink, useHistory } from "react-router-dom";
import { postUserData } from "../../services/users.services";
import { useKeycloak } from "@react-keycloak/web";

export default function Register() {
  const { keycloak, initialized } = useKeycloak();
  const [firstName, setFirstname] = useState('');
  const [lastName, setLastname] = useState('');
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');


  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const value = {email, password, username, firstName, lastName}
      await postUserData(value)
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="register">
      <div className="top">
        <div className="wrapper">
          <img
            className="logo"
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Netflix_2015_logo.svg/2560px-Netflix_2015_logo.svg.png"
            alt=""
          />
          {!keycloak.authenticated && (
            // <NavLink to="/login" >
            <button className="loginButton"  onClick={() => keycloak.login()}>Sign In</button>
          )}
        </div>
      </div>
      <div className="container">
        <h1>Unlimited movies, TV shows, and more.</h1>
        <h2>Watch anywhere. Cancel anytime.</h2>
        <br/>
        <p>
          Ready to watch? Enter your email to create or restart your membership.
        </p>
        <br/>
        
        <form className="input" onSubmit={handleSubmit}>
          <input type="email" placeholder="email address" onChange={(e) => setEmail(e.target.value)} />
          <input type="firstName" placeholder="firstName" onChange={(e) => setFirstname(e.target.value)} />
          <input type="lastName" placeholder="lastName" onChange={(e) => setLastname(e.target.value)}/>
          <input type="username" placeholder="username" onChange={(e) => setUsername(e.target.value)} />
          <input type="password" placeholder="password" onChange={(e) => setPassword(e.target.value)} />
          <button className="registerButton" >
            Start
          </button>
        </form>
      </div>
    </div>
  );
}
