import { Navbar, Container, Col } from "react-bootstrap";

const Footer = () => {
  let fullYear = new Date().getFullYear();

  return (
    <Navbar fixed="bottom" bg="dark" variant="dark" size="sm">
      <Container>
        <Col lg={12} className="text-center text-muted">
          <div>
            {fullYear}-{fullYear + 1} All rights reserved with embedtech
          </div>
        </Col>
      </Container>
    </Navbar>
  );
};

export default Footer;
