import { Link, NavLink } from "react-router-dom";
import { useNavigate } from "react-router";
import { useDispatch, useSelector } from "react-redux";
import { setUser } from "../../redux/userReducer";
import { STUDENT, FACULTY, ADMIN } from "../../constants/constants";
import { getUser, hasToken, removeTokens } from "../../service/tokenService";
import { useEffect } from "react";

const Header = () => {
  const user = useSelector((state) => state.user);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(setUser());
  }, []);

  const onLogoutClicked = (e) => {
    e.preventDefault();
    removeTokens();
    dispatch(setUser());
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-default navbar-trans navbar-expand-lg fixed-top bg-gray border">
      <div className="container">
        <button
          className="navbar-toggler collapsed"
          type="button"
          data-toggle="collapse"
          data-target="#navbarDefault"
          aria-controls="navbarDefault"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span></span>
          <span></span>
          <span></span>
        </button>

        <Link className="navbar-brand text-brand" to="/">
          Alumni Tracking System
        </Link>
        <button
          type="button"
          className="btn btn-link nav-search navbar-toggle-box-collapse d-md-none"
          data-toggle="collapse"
          data-target="#navbarTogglerDemo01"
          aria-expanded="false"
        >
          <span className="fa fa-search" aria-hidden="true"></span>
        </button>
        <div
          className="navbar-collapse collapse justify-content-center"
          id="navbarDefault"
        >
          <ul className="navbar-nav">
            {user.role === ADMIN ? (
              <>
                {/* Admin specific links */}
                <NavLink end className="nav-link" to="/admin/users">
                  Users
                </NavLink>
              </>
            ) : null}

            {user.role === STUDENT ? (
              <>
                {/* Student specific links */}

                <li className="nav-item">
                  <NavLink end className="nav-link" to="/jobs">
                    Jobs
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink end className="nav-link" to="/my-jobs">
                    My Job Advertisements
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink end className="nav-link" to="/update-profile">
                    Update Profile
                  </NavLink>
                </li>
              </>
            ) : null}

            {user.role === FACULTY ? (
              <>
                {/* Faculty specific roles */}

                <li className="nav-item">
                  <NavLink end className="nav-link" to="/jobs">
                    Jobs
                  </NavLink>
                </li>

                <li className="nav-item">
                  <NavLink end className="nav-link" to="/students">
                    Students
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink end className="nav-link" to="/update-profile">
                    Update Profile
                  </NavLink>
                </li>
              </>
            ) : null}

            {!hasToken() ? (
              <>
                <li className="nav-item">
                  <Link className="nav-link" to="/login">
                    <i className="fa fa-sign-in" aria-hidden="true"></i> Login
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/register">
                    <i className="fa fa-user-plus" aria-hidden="true"></i>{" "}
                    Register
                  </Link>
                </li>
              </>
            ) : (
              <li className="nav-item">
                <a className="nav-link" onClick={onLogoutClicked} href="">
                  <i className="fa fa-sign-out" aria-hidden="true"></i>
                  Logout
                </a>
              </li>
            )}
          </ul>
        </div>
      </div>
      {hasToken() && (
        <div style={{ position: "absolute", right: "10px" }}>
          Welcome {`${user.name} (${user.role})`}
        </div>
      )}
    </nav>
  );
};

export default Header;
