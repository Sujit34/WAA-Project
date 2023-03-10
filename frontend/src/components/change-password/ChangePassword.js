import { useEffect, useRef } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import axios from "axios";
import BodyContainer from "../body-container/BodyContainer";

const ChangePassword = () => {
  const [searchParams] = useSearchParams();
  const formRef = useRef(null);
  const navigate = useNavigate();

  useEffect(() => {
    if (!searchParams.get("changeToken")) {
      navigate("/");
    }
  }, []);

  const onSubmitClicked = (event) => {
    event.preventDefault();

    const form = formRef.current;

    if (!formRef.current) return;

    const formData = {
      token: searchParams.get("changeToken"),
      newPassword: form["password"].value,
    };

    axios
      .post("authenticate/change-password", formData)
      .then(() => {
        navigate("/login");
      })
      .catch((error) => {
        console.log(error.response.data);
      });
  };

  return (
    <BodyContainer>
      <h1 className="mb-4">Change Password</h1>
      <form
        className="form-a contactForm"
        onSubmit={onSubmitClicked}
        ref={formRef}
      >
        <div className="row">
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="password" className="form-label">
                Enter New Password:
              </label>
              <input
                id="password"
                name="password"
                type="password"
                className="form-control form-control-lg form-control-a"
                required
              />
            </div>
          </div>
          <div className="col-md-12 text-right">
            <button type="submit" className="btn btn-primary">
              Change Password
            </button>
          </div>
        </div>
      </form>
    </BodyContainer>
  );
};

export default ChangePassword;
