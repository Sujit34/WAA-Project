import { useRef, useState } from "react";
import axios from "axios";
import BodyContainer from "../body-container/BodyContainer";

const PasswordReset = () => {
  const formRef = useRef(null);
  const [errorMessageState, setErrorMessageState] = useState(null);
  const [successMessageState, setSuccessMessageState] = useState(null);
  const [errorMessageVisibilityState, setErrorMessageVisibilityState] =
    useState(false);
  const [successMessageVisibilityState, setSuccessMessageVisibilityState] =
    useState(false);

  // useEffect(() => {
  // }, [errorMessageState, successMessageState]);

  const onSubmitClicked = (event) => {
    event.preventDefault();

    if (!formRef.current) return;

    const formData = {
      email: formRef.current.email?.value,
    };

    axios
      .post("authenticate/reset-password", formData)
      .then((response) => {
        setSuccessMessageState(
          "Please check your email for the link to reset your password"
        );
        setSuccessMessageVisibilityState(true);
      })
      .catch((error) => {
        setErrorMessageState(error.response.data.error.message);
        setErrorMessageVisibilityState(true);
      });
  };

  return (
    <BodyContainer>
      <h1 className="mb-4">Reset Password</h1>
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
                required
              />
            </div>
            <div
              id="sendmessage"
              style={{
                display: successMessageVisibilityState ? "block" : "none",
              }}
            >
              {successMessageState}
            </div>
            <div
              id="errormessage"
              style={{
                display: errorMessageVisibilityState ? "block" : "none",
              }}
            >
              {errorMessageState}
            </div>
          </div>
          <div className="col-md-12 text-right">
            <button type="submit" className="btn btn-a">
              Reset Password
            </button>
          </div>
        </div>
      </form>
    </BodyContainer>
  );
};

export default PasswordReset;
