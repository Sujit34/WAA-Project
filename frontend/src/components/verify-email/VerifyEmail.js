import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import BodyContainer from "../body-container/BodyContainer";

const VerifyEmail = () => {
  const formRef = useRef(null);
  const navigate = useNavigate();

  const onSubmitClicked = (event) => {
    event.preventDefault();

    if (!formRef.current) return;

    const formData = {
      email: formRef.current.email.value,
      token: formRef.current.token.value,
    };

    axios
      .post("authenticate/verify-email", formData)
      .then(() => {
        navigate("/login");
      })
      .catch((err) => {
        console.log(err.response.data.error.message);
      });
  };

  return (
    <BodyContainer>
      <h1 className="mb-4">Verify Account</h1>
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
            <div className="form-group">
              <label htmlFor="token" className="form-label">
                OTP:
              </label>
              <input
                id="token"
                name="token"
                type="text"
                className="form-control form-control-lg form-control-a"
                minLength={5}
                required
              />
            </div>
          </div>
          <div className="col-md-12 text-right">
            <button type="submit" className="btn btn-primary">
              Verify
            </button>
          </div>
        </div>
      </form>
    </BodyContainer>
  );
};
export default VerifyEmail;
