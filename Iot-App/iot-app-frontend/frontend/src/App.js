import "./App.css";
import { Switch, Route } from "react-router-dom";
import NavigationBar from "./components/NavigationBar";
import LoginForm from "./components/LoginForm";
import Footer from "./components/Footer";
import { Container, Row, Col } from "react-bootstrap";
import AddUser from "./components/AddUser";
import SensorList from "./components/SensorList";

function App() {
  const marginTop = {
    marginTop: "20px",
  };

  return (
    <Route>
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} style={marginTop}>
            <Switch>
              <Route path="/" exact>
                <LoginForm />
              </Route>
              <Route path="/add-user" exact>
                <AddUser />
              </Route>
              <Route path="/devices" exact>
                <SensorList />
              </Route>
              <Route path="/logout" exact>
                <LoginForm />
              </Route>
            </Switch>
          </Col>
        </Row>
      </Container>

      <Container>
        <Footer />
      </Container>
    </Route>
  );
}

export default App;
