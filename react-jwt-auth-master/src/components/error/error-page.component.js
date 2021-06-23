import React, { Component } from "react";
import { BiErrorCircle } from "react-icons/bi";


export default class ErrorPage extends Component {
  render() {
    return (
      <div className="container">
        <div className="card card-container">
          <div className="container py-3 text-center">
            <BiErrorCircle size={80} color="dark grey"/>
            <p/>
            <h3>Unexpected error!</h3>
          </div>
        </div>
      </div>
    );
  }

}
