import { useEffect, useRef } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { hasToken, setTokenData } from "../../service/tokenService";
import { Link } from "react-router-dom";
import axios from "axios";
import BodyContainer from "../body-container/BodyContainer";
import { setUser } from "../../redux/userReducer";
// import {setIsLoggingin, setLoginError} from "../../redux/loginReducer"

function Login() {
  const formRef = useRef(null);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  // const { isLoggingIn, error: loginError } = useSelector(state => state.auth);

  useEffect(() => {
    if (hasToken()) {
      navigate("/");
    }
  }, []);

  const onSubmitClicked = (event) => {
    event.preventDefault();

    const form = formRef.current;

    if (!form) return;

    const credentials = {
      email: form["email"].value,
      password: form["password"].value,
    };

    axios
      .post("/authenticate/login", credentials)
      .then((response) => {
        setTokenData(response.data);
        dispatch(setUser());
        navigate("/");
      })
      .catch((err) => {
        let message = err.response.data.error.message;
        if (message) alert(err.response.data.error.message);
        else alert("Invalid credentials");
        // dispatch(setLoginError(err.response.data.error.message));
      })
      .finally(() => {
        // dispatch(setIsLoggingin(false));
      });
  };

  return (
    <BodyContainer>
      <h1 className="mb-4">Login</h1>
      <form
        className="form-a contactForm"
        onSubmit={onSubmitClicked}
        ref={formRef}
      >
        <div className="row">
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="email" className="form-label">
                Email:
              </label>
              <input
                id="email"
                name="email"
                type="email"
                className="form-control form-control-lg form-control-a"
                minLength={5}
                required
              />
            </div>
          </div>
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="password" className="form-label">
                Password:
              </label>
              <input
                type="password"
                id="password"
                name="password"
                className="form-control form-control-lg form-control-a"
                minLength={3}
                required
              />
            </div>
          </div>

          {/* {loginError ?
            <div>User email and password do not match!</div>
            : ''} */}

          <div className="col-md-12 text-right mb-3">
            <Link className="nav-link" to="/reset-password">
              Forgot Password?
            </Link>
          </div>

          <div className="col-md-12 text-right">
            <button type="submit" className="btn btn-primary" disabled={false}>
              Login
            </button>
          </div>
        </div>
      </form>
    </BodyContainer>
  );
}

export default Login;
