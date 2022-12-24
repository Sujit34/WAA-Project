import { useEffect, useRef } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { hasToken } from "../../service/tokenService";
import BodyContainer from "../body-container/BodyContainer";

export default function Register() {
  const formRef = useRef();
  const navigate = useNavigate();

  useEffect(() => {
    if (hasToken()) {
      navigate("/");
    }
  }, []);

  function submitRegistrationData(data) {
    axios
      .post("authenticate/register", data)
      .then((response) => {
        navigate("/");
      })
  }

  function onRegisterClick(e) {
    e.preventDefault();
    const form = formRef.current;
    const data = {
      email: form.email.value,
      firstName: form.fname.value,
      lastName: form.lname.value,
      password: form.password.value,
      phoneNumber: form.phoneNumber.value,
      address: {
        street: form.street.value,
        city: form.city.value,
        state: form.state.value,
        zipCode: form.zipcode.value,
      },
      accountType: form.userType.value,
    };
    submitRegistrationData(data);
  }

  return (
    <BodyContainer>
      <h1 className="mb-4">Register</h1>
      <form className="form-a contactForm" ref={formRef}>
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
                name="password"
                id="password"
                className="form-control form-control-lg form-control-a"
                minLength={3}
                required
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="fname" className="form-label">
                First Name:
              </label>
              <input
                name="fname"
                id="fname"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="lname" className="form-label">
                Last Name:
              </label>
              <input
                name="lname"
                id="lname"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="street" className="form-label">
                Street:
              </label>
              <input
                name="street"
                id="street"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="street_address_2" className="form-label">
                Street Address 2:
              </label>
              <input
                name="street_address_2"
                id="street_address_2"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="city" className="form-label">
                City:
              </label>
              <input
                name="city"
                id="city"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="state" className="form-label">
                State:
              </label>
              <input
                name="state"
                id="state"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="zipcode" className="form-label">
                Zip Code:
              </label>
              <input
                name="zipcode"
                type="text"
                id="zipcode"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="phoneNumber" className="form-label">
                Phone Number:
              </label>
              <input
                name="phoneNumber"
                id="phoneNumber"
                type="text"
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor="userType" className="form-label">
                Account type:
              </label>
              <select
                className="form-control form-control-lg form-control-a"
                name="userType"
                id="userType"
              >
                <option value="student">Student</option>
                <option value="faculty">Faculty</option>
              </select>
            </div>
          </div>

          <div className="col-md-12 text-right mb-4">
            <button
              type="submit"
              className="btn btn-primary"
              onClick={onRegisterClick}
            >
              Register
            </button>
          </div>
        </div>
      </form>
    </BodyContainer>
  );
}
