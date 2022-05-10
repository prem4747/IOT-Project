import { React, useState } from "react";
import { useHistory } from "react-router-dom";
import AuthService from "../services/auth-service";
import { Form, Button } from "react-bootstrap";
import classes from "./LoginForm.module.css";

const LoginForm = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const history = useHistory();

  const loginFormHandler = async (e) => {
    e.preventDefault();
    try {
      await AuthService.login(email, password).then(
        () => {
          history.replace("/devices");

          // window.location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
      <div>
        <br />
        <section className={classes.auth}>
          <Form onSubmit={loginFormHandler}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>
                <h4>
                  <b>Login</b>
                </h4>
              </Form.Label>
              <Form.Control
                required
                type="email"
                placeholder="Enter email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <Form.Text className="text-muted"></Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Control
                required
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Form.Group>

            <Button variant="dark" type="submit">
              Submit
            </Button>
          </Form>
        </section>
      </div>
    </>
  );
};

export default LoginForm;
