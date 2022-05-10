import React, { useState, useEffect, useCallback } from "react";
import { Card, Table, Button, InputGroup, FormControl } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBackwardStep,
  faStepForward,
} from "@fortawesome/free-solid-svg-icons";
import AuthService from "../services/auth-service";
import authHeader from "../services/auth-header";

const SensorList = () => {
  const [currentUser, setCurrentUser] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    if (user) {
      setCurrentUser(user);
    }
  }, [currentUser]);

  const [devices, setDevices] = useState({
    listOfDevices: {},
    devicesPerPage: 10,
  });
  const [pageNumber, setPageNumber] = useState(1);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchDeviceHandler = useCallback(async () => {
    setIsLoading(true);
    setError(null);
    console.log(pageNumber);
    let currentPage = pageNumber - 1;
    try {
      console.log("inside fetchDeviceHandler");
      console.log(authHeader());
      const response = await fetch(
        "http://localhost:8081/rest/sensor?page=" +
          currentPage +
          "&size=" +
          devices.devicesPerPage,
        { headers: authHeader() }
      );

      if (!response.ok) {
        throw new Error("Something went wrong!");
      }
      const data = await response.json();
      console.log(data);

      setDevices({
        listOfDevices: data.content,
        totalPages: data.totalPages,
        devicesPerPage: 10,
        totalElements: data.totalElements,
      });
    } catch (error) {
      setError(error.message);
    }
    setIsLoading(false);
  }, [devices.devicesPerPage, pageNumber]);

  useEffect(() => {
    console.log("Initial Load");
    fetchDeviceHandler();
  }, [pageNumber, fetchDeviceHandler]);

  let content = (
    <tr align="center">
      <td colSpan="6">No Devices Available</td>
    </tr>
  );

  if (devices.listOfDevices.length > 0) {
    content = devices.listOfDevices.map((device) => (
      <tr key={device.deviceId}>
        <td>{device.deviceId}</td>
        <td>{device.temperature}</td>
        <td>{device.batteryVoltage}</td>
        <td>{device.status}</td>
        <td>{device.timestamp}</td>
      </tr>
    ));
  }

  if (error) {
    content = content = (
      <tr align="center">
        <td colSpan="6">
          <h5>
            <b>{error}</b>
          </h5>
        </td>
      </tr>
    );
  }

  if (isLoading) {
    content = (
      <tr align="center">
        <td colSpan="6">
          <h5>
            <b>Loading...</b>
          </h5>
        </td>
      </tr>
    );
  }

  const pageNumCss = {
    width: "45px",
    border: "1px solid black",
    backgroundColor: "white",
    color: "black",
    textAlign: "center",
    fontWeight: "bold",
  };

  const changePageHandler = (event) => {
    let targetPage = parseInt(event.target.value);
    setPageNumber(targetPage);
  };

  const prevPageHandler = () => {
    let prevPage = 1;
    if (pageNumber > prevPage) {
      setPageNumber((prevState) => prevState - 1);
      fetchDeviceHandler();
    }
  };

  const nextPageHandler = () => {
    if (
      pageNumber < Math.ceil(devices.totalElements / devices.devicesPerPage)
    ) {
      console.log("increasing page number");

      setPageNumber((prevState) => prevState + 1);
      fetchDeviceHandler();
    }
  };

  return (
    <>
      {currentUser && (
        <div>
          <Button
            onClick={fetchDeviceHandler}
            className="float-end"
            variant="dark"
            type="submit"
            size="sm"
          >
            Refresh
          </Button>

          <br></br>
          <br></br>
          <Card border="dark" size="sm">
            <Card.Header variant="dark">
              <h4>
                <b>Device Info</b>
              </h4>
            </Card.Header>

            <Card.Body>
              <Table bordered hover striped size="sm">
                <thead>
                  <tr>
                    <th>Device ID</th>
                    <th>Temperature</th>
                    <th>Battery Voltage</th>
                    <th>Status</th>
                    <th>Timestamp</th>
                  </tr>
                </thead>
                <tbody>{content}</tbody>
              </Table>
            </Card.Body>
            {devices.listOfDevices.length > 0 ? (
              <Card.Footer>
                <div style={{ float: "left" }}>
                  Showing Page {pageNumber} of {devices.totalPages}
                </div>
                <div style={{ float: "right" }}>
                  <InputGroup size="sm">
                    <Button
                      type="button"
                      variant="outline-dark"
                      disabled={pageNumber === 1 ? true : false}
                      onClick={prevPageHandler}
                    >
                      <FontAwesomeIcon icon={faBackwardStep} /> Prev
                    </Button>

                    <FormControl
                      style={pageNumCss}
                      name="currentPage"
                      value={pageNumber}
                      onChange={changePageHandler}
                    />

                    <Button
                      type="button"
                      variant="outline-dark"
                      disabled={
                        pageNumber === devices.totalPages ? true : false
                      }
                      onClick={nextPageHandler}
                    >
                      <FontAwesomeIcon icon={faStepForward} /> Next
                    </Button>
                  </InputGroup>
                </div>
              </Card.Footer>
            ) : null}
          </Card>
        </div>
      )}
    </>
  );
};

export default SensorList;
