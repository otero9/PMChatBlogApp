import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import DatePicker from "react-datepicker";
import { isEmail } from "validator";
import "react-datepicker/dist/react-datepicker.css";
import UserService from "../../services/user.service";
import { CgProfile } from "react-icons/cg";
import { BsPersonFill } from "react-icons/bs";
import { FiMail, FiCalendar } from "react-icons/fi";


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

export default class UpdateProfile extends Component {

  constructor(props) {
    super(props);
    this.handleUpdateProfile = this.handleUpdateProfile.bind(this);
    this.onChangeUsername = this.onChangeUsername.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangeFirstName = this.onChangeFirstName.bind(this);
    this.onChangeSurnames = this.onChangeSurnames.bind(this);
    this.onChangeBirthDate = this.onChangeBirthDate.bind(this);

    this.state = {
      redirect: null,
      userReady: false,
      currentUser: { username: "" },
      username: "",
      email: "",
      firstname: "",
      surnames: "",
      birthdate: "",
      successful: false,
      message: ""
    };
  }

  onChangeUsername(e) {
    this.setState({
      username: e.target.value
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
      firstname: e.target.value
    });
  }

  onChangeSurnames(e) {
    this.setState({
      surnames: e.target.value
    });
  }

  onChangeBirthDate(e) {
    this.setState({
      birthdate: e
    });
  }

  handleUpdateProfile(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      UserService.updateProfile(
        this.state.currentUser.id,
        this.state.username,
        this.state.email,
        this.state.firstname,
        this.state.surnames,
        this.state.birthdate
      ).then(
        (response) => {
          UserService.logout();
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

  componentDidMount() {
    const currentUser = UserService.getCurrentUser();
    if (!currentUser) this.setState({ redirect: "/login" });
    this.setState({
        currentUser: currentUser,
        userReady: true,
        username: currentUser.username,
        email: currentUser.email,
        firstname: currentUser.firstname,
        surnames: currentUser.lastname
    });
  }

  render() {

    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (

      <div className="card card-container">
          {(currentUser) ? (
            <div>
              <div className="container py-3 text-center">
                <CgProfile size={80} color="dark grey"/>
              </div>
              <Form
                onSubmit={this.handleUpdateProfile}
                ref={c => {
                  this.form = c;
                }}>
                {!this.state.successful && (
                  <div>
                    <div className="form-group">
                      <label htmlFor="username"><BsPersonFill size="20"/> Username</label>
                      <Input
                        type="text"
                        className="form-control"
                        name="username"
                        value={this.state.username}
                        onChange={this.onChangeUsername}
                        validations={[required, vusername]}
                      />
                    </div>
                    <div className="form-group">
                      <label htmlFor="firstname"><BsPersonFill size="20"/> First name</label>
                      <Input
                        type="text"
                        className="form-control"
                        name="firstname"
                        value={this.state.firstname}
                        onChange={this.onChangeFirstName}
                        validations={[required, vfirstname]}
                      />
                    </div>
                    <div className="form-group">
                      <label htmlFor="surnames"><BsPersonFill size="20"/> Last name</label>
                      <Input
                        type="text"
                        className="form-control"
                        name="firstname"
                        value={this.state.surnames}
                        onChange={this.onChangeSurnames}
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
                        <label htmlFor="birhtdate"><FiCalendar size="20"/> Birth date</label>&nbsp;
                        <DatePicker
                        selected={this.state.birthdate}
                          onChange={this.onChangeBirthDate}
                          dateFormat="dd/MM/yyyy"
                          validations={[required, vbirthdate]}/>
                    </div>
                    <div className="form-group">
                      <button className="btn btn-primary btn-block">Update</button>
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
            ): (<Redirect to="/login"/>)}
      </div>

    );
  }
}
