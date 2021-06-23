import React, { Component } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import Textarea from "react-validation/build/textarea";
import CheckButton from "react-validation/build/button";
import VideoService from "../../services/video.service";
import Select from "react-select";
import CategoryService from "../../services/category.service";
import UserService from "../../services/user.service";
import { Redirect } from "react-router-dom";
import { BiVideo } from "react-icons/bi";
import validator from 'validator';

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const vVideoTitle = value => {
  if (value.length < 1 || value.length > 200) {
    return (
      <div className="alert alert-danger" role="alert">
        The Title must be between 1 and 200 characters.
      </div>
    );
  }
};

const vVideoUrl = value => {
  if (!validator.isURL(value)){
    return (
      <div className="alert alert-danger" role="alert">
        Invalid URL.
      </div>
    );
  }
};

const vVideoDescription = value => {
  if (value.length < 1) {
    return (
      <div className="alert alert-danger" role="alert">
        The Description can not be empty.
      </div>
    );
  }
};

export default class UpdateVideo extends Component {

  constructor(props) {
    super(props);
    this.handleUpdateVideo = this.handleUpdateVideo.bind(this);
    this.onChangeVideoTitle = this.onChangeVideoTitle.bind(this);
    this.onChangeVideoDescription = this.onChangeVideoDescription.bind(this);
    this.onChangeVideoUrl = this.onChangeVideoUrl.bind(this);
    this.onChangeCategory = this.onChangeCategory.bind(this);

    this.state = {
      currentUser: undefined,
      userReady: false,
      categories: [],
      selectedCategory: "",
      videoUrl: "",
      videoTitle: "",
      videoDescription: "",
      successful: false,
      message: "",
      video: undefined,
      videoId: this.props.match.params.id
    };

  }

  handleUpdateVideo(e) {

    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();
    if (this.checkBtn.context._errors.length === 0) {
      VideoService.updateVideo(
        this.state.videoId,
        this.state.selectedCategory.value,
        this.state.videoUrl,
        this.state.videoTitle,
        this.state.videoDescription
      ).then(
        response => {
          if (response.status === 200) {
            this.props.history.push("/video/"+this.state.videoId);
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
  }

  onChangeVideoTitle(e) {
    this.setState({
      videoTitle: e.target.value
    });
  }

  onChangeVideoUrl(e) {
    this.setState({
      videoUrl: e.target.value
    });
  }

  onChangeVideoDescription(e) {
    this.setState({
      videoDescription: e.target.value
    });
  }

  onChangeCategory(e) {
    this.setState({
      selectedCategory: e
    });
  }

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

    VideoService.getVideoById(this.state.videoId).then(
      response => {
        if (response.status === 200) {
          this.setState({
            video: response.data,
            selectedCategory: { value: response.data.categoryId, label: response.data.categoryName },
            videoTitle: response.data.videoTitle,
            videoDescription: response.data.videoDescription,
            videoUrl: response.data.videoUrl
          });
        } else {
          this.props.history.push("/error");
          window.location.reload();
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
      }
    );

    const currentUser = UserService.getCurrentUser();
    if (!currentUser) this.setState({ redirect: "/login" });
    this.setState({
        currentUser: currentUser,
        userReady: true
    });

  }

  render() {

    return (
      <div>
        <br/>
        <div className="container card">
          {(this.state.userReady) ? (
              <div className="container">
                <header className="jumbotron py-6 text-center">
                    <h4><BiVideo/>&nbsp;&nbsp;Update Video</h4>
                </header>
                <Form onSubmit={this.handleUpdateVideo}
                  ref={c => {
                    this.form = c;
                  }}>
                {!this.state.successful && (
                  <div>
                    <div className="form-group">
                      <label htmlFor="category">Category</label>
                      <Select
                        defaultValue={{ value: this.state.selectedCategory.categoryId, label: this.state.selectedCategory.categoryName }}
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
                        value={this.state.videoTitle}
                        onChange={this.onChangeVideoTitle}
                        validations={[required, vVideoTitle]}
                      />
                    </div>
                    <div className="form-group">
                      <label htmlFor="header">URL</label>
                      <Input
                        type="text"
                        className="form-control"
                        name="header"
                        maxLength="200"
                        value={this.state.videoUrl}
                        onChange={this.onChangeVideoUrl}
                        validations={[required, vVideoUrl]}
                      />
                    </div>
                    <div className="form-group">
                      <label htmlFor="body">Description</label>
                      <Textarea
                        type="textarea"
                        className="form-control"
                        name="body"
                        value={this.state.videoDescription}
                        onChange={this.onChangeVideoDescription}
                        validations={[required, vVideoDescription]}
                      />
                    </div>
                    <div className="form-group  text-center">
                      <button className="btn btn-primary">Update Video</button>
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
