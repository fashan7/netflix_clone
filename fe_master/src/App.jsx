import "./app.scss"
import Home from "./pages/home/Home";
import Register from "./pages/register/Register";
import Watch from "./pages/watch/Watch";
import Login from "./pages/login/Login";
import Series from "./pages/series/Series";
import Movies from "./pages/movies/Movies";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";

import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./Keycloak";
import PrivateRoute from "./helpers/PrivateRoute";


const App = () => {
  let { user } = false
  var tokenAccess = sessionStorage.getItem("sessionToken");
  if (tokenAccess != null) {
    user = true
  }
  
  return (
    <>
    <ReactKeycloakProvider authClient={keycloak}>
      <Router>
        <Switch>
          <Route exact path="/">
            {user ? <Home /> : <Redirect to="/login" />}
            {/* <PrivateRoute>
              <Home />
            </PrivateRoute> */}
            {/* <Home /> */}
            
          </Route>
          <Route path="/register">
            {!user ? <Register /> : <Redirect to="/" />}
          </Route>
          <Route path="/login">{!user ? <Login /> : <Redirect to="/" />}</Route>
          {user && (
            <>
              <Route path="/movies">
                <Movies />
              </Route>
              <Route path="/series">
                <Series />
              </Route>
              <Route path="/watch">
                <Watch />
              </Route>
            </>
          )} 
        </Switch>
      </Router>
    </ReactKeycloakProvider>
{/* <Home /> */}

    </>
  );
};

export default App;