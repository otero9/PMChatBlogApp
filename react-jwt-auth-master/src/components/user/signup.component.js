import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import DatePicker from "react-datepicker";
import { isEmail } from "validator";
import "react-datepicker/dist/react-datepicker.css";
import UserService from "../../services/user.service";
import { CgProfile } from "react-icons/cg";
import { BsPersonFill, BsLockFill } from "react-icons/bs";
import { FiMail, FiCalendar } from "react-icons/fi";
import { Link } from "react-router-dom";


const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const email = value => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vusername = value => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The UserName must be between 3 and 20 characters.
      </div>
    );
  }
};

const vpassword = value => {
  if (value.length < 6 || value.length > 40) {
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  }
};

const vfirstname = value => {
  if (value.length < 1 || value.length > 200) {
    return (
      <div className="alert alert-danger" role="alert">
        The FirstName must be between 1 and 200 characters.
      </div>
    );
  }
};

const vsurnames = value => {
  if (value.length < 1 || value.length > 200) {
    return (
      <div className="alert alert-danger" role="alert">
        The Surnames must be between 1 and 200 characters.
      </div>
    );
  }
};

const vbirthdate = value => {
  if (value.length < 1) {
    return (
      <div className="alert alert-danger" role="alert">
        The BirthDate it´s mandatory!
      </div>
    );
  }
};

export default class Signup extends Component {

  constructor(props) {
    super(props);
    this.handleSignup = this.handleSignup.bind(this);
    this.onChangeUserName = this.onChangeUserName.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onChangeFirstName = this.onChangeFirstName.bind(this);
    this.onChangeLastName = this.onChangeLastName.bind(this);
    this.onChangeBirthDate = this.onChangeBirthDate.bind(this);

    this.state = {
      userName: "",
      email: "",
      password: "",
      firstName: "",
      lastName: "",
      birthdate: "",
      location: "",
      webSite: "",
      biography: "",
      pmiProfile: "",
      notificationEmails: false,
      marketingEmails: false,
      successful: false,
      message: ""
    };
  }

  onChangeUserName(e) {
    this.setState({
      userName: e.target.value
    });
  }

  onChangeEmail(e) {
    this.setState({
      email: e.target.value
    });
  }

  onChangePassword(e) {
    this.setState({
      password: e.target.value
    });
  }

  onChangeFirstName(e) {
    this.setState({
      firstName: e.target.value
    });
  }

  onChangeLastName(e) {
    this.setState({
      lastName: e.target.value
    });
  }

  onChangeBirthDate(e) {
    this.setState({
      birthdate: e
    });
  }

  handleSignup(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      UserService.signup(
        this.state.userName,
        this.state.email,
        this.state.password,
        this.state.firstName,
        this.state.lastName,
        this.state.birthdate,
        this.state.location,
        this.state.webSite,
        this.state.biography,
        this.state.pmiProfile,
        this.state.notificationEmails,
        this.state.marketingEmails
      ).then(
        response => {
          this.setState({
            message: response.data.message,
            successful: true
          });
          this.props.history.push("/login");
          window.location.reload();
        },
        error => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();

          this.setState({
            successful: false,
            message: resMessage
          });
        }
      );
    }
  }

  render() {
    return (
      <div className="card card-container">
          <div className="container py-3 text-center">
            <CgProfile size={80} color="dark grey"/>
          </div>
          <Form
            onSubmit={this.handleSignup}
            ref={c => {
              this.form = c;
            }}>
            {!this.state.successful && (
              <div>
                <div className="form-group">
                  <label htmlFor="userName"><BsPersonFill size="20"/> Username</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="userName"
                    value={this.state.userName}
                    onChange={this.onChangeUserName}
                    validations={[required, vusername]}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="firstName"><BsPersonFill size="20"/> First name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="firstName"
                    value={this.state.firsfirstNametname}
                    onChange={this.onChangeFirstName}
                    validations={[required, vfirstname]}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="lastname"><BsPersonFill size="20"/> Last name</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="lastName"
                    value={this.state.lastName}
                    onChange={this.onChangeLastName}
                    validations={[required, vsurnames]}
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="email"><FiMail size="20"/> Email</label>
                  <Input
                    type="text"
                    className="form-control"
                    name="email"
                    value={this.state.email}
                    onChange={this.onChangeEmail}
                    validations={[required, email]}/>
                </div>
                <div className="form-group">
                  <label htmlFor="password"><BsLockFill size="20"/> Password</label>
                  <Input
                    type="password"
                    className="form-control"
                    name="password"
                    value={this.state.password}
                    onChange={this.onChangePassword}
                    validations={[required, vpassword]}/>
                </div>
                <div className="form-group">
                    <label htmlFor="birhtdate"><FiCalendar size="20"/> Birth date</label>&nbsp;
                    <DatePicker
                      selected={this.state.birthdate}
                      onChange={this.onChangeBirthDate}
                      dateFormat="dd/MM/yyyy"
                      validations={[required, vbirthdate]}/>
                </div>
                <div className="form-group">
                  <button className="btn btn-primary btn-block">Register</button>
                </div>
                <div className="container py-3 text-center">
                  <Link to="/login">You already have an account?</Link>
                </div>
              </div>
            )}
            {this.state.message && (
              <div className="form-group">
                <div className={
                    this.state.successful
                      ? "alert alert-success"
                      : "alert alert-danger"
                  }
                  role="alert">
                  {this.state.message}
                </div>
              </div>
            )}
            <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
      </div>
    );
  }
}
