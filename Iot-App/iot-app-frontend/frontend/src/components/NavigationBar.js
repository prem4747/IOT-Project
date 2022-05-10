import { React, useState, useEffect } from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import { Link } from "react-router-dom";
import AuthService from "../services/auth-service";

const NavigationBar = () => {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
    }
  }, [currentUser]);

  const logOut = () => {
    AuthService.logout();
    setCurrentUser(undefined);
  };

  return (
    <div>
      <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
        <Container>
          <Navbar.Brand href="">IOT Dashboard</Navbar.Brand>
          <Nav className="ms-auto"></Nav>
          <Nav className="justify-content-end">
            {currentUser && (
              <Link to={"add-user"} className="nav-link">
                Add User
              </Link>
            )}
          </Nav>
        </Container>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="ms-auto"></Nav>
          <Nav className="justify-content-end">
            {!currentUser && (
              <Link to={"/"} className="nav-link">
                Login
              </Link>
            )}
            {currentUser && (
              <Link to={"devices"} className="nav-link">
                Devices
              </Link>
            )}
            {currentUser && (
              <Link to={"logout"} className="nav-link" onClick={logOut}>
                Logout
              </Link>
            )}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
};

export default NavigationBar;
