import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import UserService from "../../services/user.service";
import { CgProfile } from "react-icons/cg";
import { BsLockFill } from "react-icons/bs";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { Link } from "react-router-dom";
import ErrorPage from "../error/error-page.component";


const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
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

export default class ChangePassword extends Component {

  constructor(props) {
    super(props);
    this.handleChangePassword = this.handleChangePassword.bind(this);
    this.onChangeOldPassword = this.onChangeOldPassword.bind(this);
    this.onChangeNewPassword = this.onChangeNewPassword.bind(this);

    this.state = {
      oldPassword: "",
      newPassword: "",
      redirect: null,
      userReady: false,
      currentUser: { username: "" },
      successful: false,
      message: ""
    };
  }

  onChangeOldPassword(e) {
    this.setState({
      oldPassword: e.target.value
    });
  }

  onChangeNewPassword(e) {
    this.setState({
      newPassword: e.target.value
    });
  }

  handleChangePassword(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      UserService.changePassword(
        this.state.currentUser.id,
        this.state.oldPassword,
        this.state.newPassword
      ).then(
      response => {
        if (response.status === 200) {
          UserService.logOut();
          this.props.history.push("/login");
          window.location.reload();
        }
        if (response.status !== 200) {
          return <ErrorPage/>;
        }
      },
      error => {
        this.props.history.push("/error");
        window.location.reload();
        this.setState({
          content:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        });
      });
    }
  }

  componentDidMount() {
    const currentUser = UserService.getCurrentUser();

    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ currentUser: currentUser, userReady: true })
  }

  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (
      <div className="card card-container">
          <div className="container py-3">
              <header className="jumbotron py-4 text-center">
                  <CgProfile size={50} color="dark grey"/>
                  <h2>{currentUser.username}</h2>
              </header>
              <Form
                onSubmit={this.handleChangePassword}
                ref={c => {
                  this.form = c;
                }}>
                {!this.state.successful && (
                  <div>
                    <div className="form-group">
                      <label htmlFor="password"><BsLockFill size="20"/> Old password</label>
                      <Input
                        type="password"
                        className="form-control"
                        name="password"
                        value={this.state.password}
                        onChange={this.onChangeOldPassword}
                        validations={[required, vpassword]}/>
                    </div>
                    <div className="form-group">
                      <label htmlFor="password"><BsLockFill size="20"/> New password</label>
                      <Input
                        type="password"
                        className="form-control"
                        name="password"
                        value={this.state.password}
                        onChange={this.onChangeNewPassword}
                        validations={[required, vpassword]}/>
                    </div>
                    <div className="form-group text-center">
                      <button className="btn btn-primary">Change password</button>
                    </div>
                    <div className="container py-3 text-center">
                      <Link to="/profile">You don´t want to change the password?</Link>
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
              <CheckButton style={{ display: "none" }} ref={c => {this.checkBtn = c;}}/>
            </Form>
          </div>
      </div>
    );
  }

}
