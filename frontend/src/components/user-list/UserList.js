import axios from "axios";
import React, { useEffect, useState } from "react";
import BodyContainer from "../body-container/BodyContainer";
import AdminChangePassword from "../change-password/AdminChangePassword";

function UserList() {
  const [refreshFlagState, setRefreshFlagState] = useState(false);
  const [users, setUsers] = useState([]);

  useEffect(() => {
    axios
      .get("/admin/users")
      .then((response) => {
        setUsers(response.data);
      })
      .catch((error) => {
        console.log(error.message);
      });
  }, [refreshFlagState]);

  const onActivateDeactivateClicked = (id, activated) => {
    updateUser(id, {
      activated: !activated,
    });
  };

  const onDeleteClicked = (id) => {
    if (window.confirm("Confirm delete")) updateUser(id, { deleted: true });
    const newUsers = users.filter((user) => user.id !== id);
    setUsers(newUsers);
  };

  const updateUser = (id, currentStatus) => {
    axios
      .put(`/admin/users/${id}`, currentStatus)
      .then(() => {
        setRefreshFlagState(!refreshFlagState);
      })
      .catch((error) => {
        console.log(error.message);
      });
  };

  return (
    <BodyContainer>
      <section className="intro-single p-2">
        <div className="container">
          <div className="row">
            <div className="title-single-box">
              <h1 className="title-single">Users</h1>
            </div>
          </div>
        </div>
      </section>
      <table className="table table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">User Type</th>
            <th scope="col">Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((item, index) => {
            return (
              <tr key={index}>
                <th scope="row">{item.id}</th>
                <td>{item.firstName}</td>
                <td>{item.lastName}</td>
                <td>{item.email}</td>
                <td>{item.userType}</td>
                <td>
                  <button
                    className="btn btn-sm btn-outline-warning mr-3"
                    onClick={() =>
                      onActivateDeactivateClicked(item.id, item.activated)
                    }
                  >
                    {item.activated ? "Deactivate" : "Activate"}
                  </button>

                  <AdminChangePassword id={item.id} />

                  <button
                    className="btn btn-sm btn-outline-danger"
                    onClick={() => onDeleteClicked(item.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </BodyContainer>
  );
}

export default UserList;
