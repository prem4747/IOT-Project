import { Form, Button } from "react-bootstrap";
import classes from "./LoginForm.module.css";

const AddUser = () => {
  return (
    <section className={classes.auth}>
      <Form>
        <Form.Label>
          <h4>
            <b>Add New User</b>
          </h4>
        </Form.Label>

        <Form.Group className="mb-3" controlId="formBasicUsername">
          <Form.Control required type="text" placeholder="Username" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Control required type="email" placeholder="Email" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Control required type="password" placeholder="Password" />
        </Form.Group>

        <Form.Group className="mb-3" controlId="formBasicRole">
          <Form.Label>
            <b>Select Role</b>
          </Form.Label>
          <Form.Control required as="select" custom>
            <option key={"empty"} value={""}></option>
            <option key={"1"} value={"1"}>
              Admin
            </option>
            <option key={"2"} value={"2"}>
              Standard User
            </option>
          </Form.Control>
        </Form.Group>
        <Button variant="dark" type="submit">
          Submit
        </Button>
      </Form>
    </section>
  );
};

export default AddUser;
