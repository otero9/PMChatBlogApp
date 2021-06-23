import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import Textarea from "react-validation/build/textarea";
import CheckButton from "react-validation/build/button";
import TemplateService from "../../services/template.service";
import Select from "react-select";
import CategoryService from "../../services/category.service";
import UserService from "../../services/user.service";
import { Redirect } from "react-router-dom";
import { CgTemplate } from "react-icons/cg";


const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const vTemplateTitle = value => {
  if (value.length < 1 || value.length > 200) {
    return (
      <div className="alert alert-danger" role="alert">
        The Title must be between 1 and 200 characters.
      </div>
    );
  }
};

const vTemplateDescription = value => {
  if (value.length < 1) {
    return (
      <div className="alert alert-danger" role="alert">
        The Description can not be empty.
      </div>
    );
  }
};


export default class UploadTemplate extends Component {

  constructor(props) {
    super(props);
    this.handleUploadTemplate = this.handleUploadTemplate.bind(this);
    this.onChangeTemplateTitle = this.onChangeTemplateTitle.bind(this);
    this.onChangeTemplateDescription = this.onChangeTemplateDescription.bind(this);
    this.onChangeCategory = this.onChangeCategory.bind(this);
    this.onFileChange = this.onFileChange.bind(this);

    this.state = {
      currentUser: undefined,
      userReady: false,
      categories: [],
      selectedCategory: "",
      templateTitle: "",
      templateDescription: "",
      file: [],
      successful: false,
      message: "",
      template: undefined
    };

  }

  handleUploadTemplate(e) {

    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });
    this.form.validateAll();
    if (this.checkBtn.context._errors.length === 0) {
      TemplateService.uploadFile(
        this.state.file
      ).then(
        response => {
          if (response.status === 201) {
            TemplateService.uploadTemplate(
              this.state.selectedCategory.value,
              this.state.currentUser.id,
              this.state.templateTitle,
              this.state.templateDescription,
              response.data.fileId).then(
                  response => {
                    if (response.status === 201) {
                      this.setState({
                        template: response.data
                      });
                      this.props.history.push("/template/"+this.state.template.templateId);
                      window.location.reload();
                    } else {
                      this.props.history.push("/error");
                      window.location.reload();
                    }
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

  onChangeTemplateTitle(e) {
    this.setState({
      templateTitle: e.target.value
    });
  }

  onChangeTemplateDescription(e) {
    this.setState({
      templateDescription: e.target.value
    });
  }

  onChangeCategory(e) {
    this.setState({
      selectedCategory: e
    });
  }

  onFileChange = event => {
      this.setState({
        file: event.target.files[0]
      });
  };

  componentWillMount() {
    CategoryService.getAllCategories().then(
      response => {
        this.setState({
          categories: response.data.map( category => ({ value: category.categoryId, label: category.categoryName }))
        });
      },
      error => {
        this.setState({
          content:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        });
      }
    );

    const user = UserService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user,
        userReady: true
      });
    }

  }

  render() {

    return (
      <div>
        <br/>
        <div className="container card">
          {(this.state.userReady) ? (
              <div className="container">
                <header className="jumbotron py-6 text-center">
                    <h4><CgTemplate/>&nbsp;&nbsp;Upload a new Template</h4>
                </header>
                <Form onSubmit={this.handleUploadTemplate}
                  ref={c => {
                    this.form = c;
                  }}>
                {!this.state.successful && (
                  <div>
                    <div className="form-group">
                      <label htmlFor="category">Category</label>
                      <Select
                        value={this.state.selectedCategory}
                        onChange={this.onChangeCategory}
                        options={this.state.categories}/>
                    </div>
                    <div className="form-group">
                      <label htmlFor="title">Title</label>
                      <Input
                        type="text"
                        className="form-control"
                        name="title"
                        maxLength="200"
                        value={this.state.templateTitle}
                        onChange={this.onChangeTemplateTitle}
                        validations={[required, vTemplateTitle]}
                      />
                    </div>

                    <div className="form-group">
                      <label htmlFor="body">Description</label>
                      <Textarea
                        type="textarea"
                        className="form-control"
                        name="body"
                        value={this.state.templateDescription}
                        onChange={this.onChangeTemplateDescription}
                        validations={[required, vTemplateDescription]}
                      />
                    </div>
                    <div className="form-group">
                      <label htmlFor="title">Upload File</label><br/>
                      <input className="btn" type="file" onChange={this.onFileChange} />
                    </div>
                    <div className="form-group  text-center">
                      <button className="btn btn-primary">Upload Template</button>
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
      <br/>
    </div>
    );
  }
}
