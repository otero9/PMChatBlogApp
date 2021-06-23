import React, { Component } from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route
  } from "react-router-dom";
import { FaUserCircle } from "react-icons/fa";
import { BsSearch } from "react-icons/bs";
import { Navbar,Nav,NavDropdown,Form,FormControl,Button } from "react-bootstrap";
import UserService from "./services/user.service";
import ChatBotService from "./services/chatbot.service";
import Login from "./components/user/login.component";
import Signup from "./components/user/signup.component";
import Home from "./components/home/home.component";
import User from "./components/user/user.component";
import Profile from "./components/user/profile.component";
import ChangePassword from "./components/user/change-password.component";
import UpdateProfile from "./components/user/update-profile.component";
import BestUsers from "./components/user/best-users.component";
import ErrorPage from "./components/error/error-page.component";
import CreateBlog from "./components/blog/new-blog.component";
import Blogs from "./components/blog/blogs.component";
import Blog from "./components/blog/blog.component";
import MyBlogs from "./components/blog/my-blogs.component";
import UpdateBlog from "./components/blog/update-blog.component";
import BestBlogs from "./components/blog/best-blogs.component";
import UploadTemplate from "./components/template/new-template.component";
import Template from "./components/template/template.component";
import Templates from "./components/template/templates.component";
import MyTemplates from "./components/template/my-templates.component";
import UpdateTemplate from "./components/template/update-template.component";
import BestTemplates from "./components/template/best-templates.component";
import UploadVideo from "./components/video/new-video.component";
import Video from "./components/video/video.component";
import Videos from "./components/video/videos.component";
import MyVideos from "./components/video/my-videos.component";
import UpdateVideo from "./components/video/update-video.component";
import BestVideos from "./components/video/best-videos.component";
import logo from "./images/logo7.png";
import { Widget, addResponseMessage } from "react-chat-widget";
import "bootstrap/dist/css/bootstrap.min.css";
import "react-chat-widget/lib/styles.css";
import "./App.css";

class App extends Component {

  constructor(props) {
    super(props);
    this.logOut = this.logOut.bind(this);

    this.state = {
      currentUser: undefined,
    };
  }

  componentDidMount() {

    addResponseMessage('Welcome to ChatBlog! How can I help you? Happy to answer your questions about Project Management!');

    const user = UserService.getCurrentUser();

    if (user) {
      this.setState({
        currentUser: user
      });
    }
  }

  logOut() {
    UserService.logout();
  }

  render() {

    const handleNewMessage = (newMessage) => {
      //console.log('New message incoming!'+newMessage);
      ChatBotService.getResponse(newMessage).then(
        response => {
          if (response.status === 200) {
            addResponseMessage(response.data.response);
          }
          if (response.status !== 200) {
            addResponseMessage('Sorry, I have not understood you correctly. Could you ask the question again in a another way? Thank you so much!');
          }
        },
        error => {
          addResponseMessage('Currently the ChatBot service is unavailable, Sorry! Please, try more later.');
        }
      );
    };

    const { currentUser } = this.state;

    return (
      <div className="row">
              <div className="col-md-12">
                  <Router>
                    <Navbar bg="dark" variant="dark" expand="lg" sticky="top">
                      &nbsp;&nbsp;&nbsp;&nbsp;
                      <Navbar.Brand href="/">
                        <img
                          alt=""
                          src={logo}
                          width="33"
                          height="33"
                          className="d-inline-block align-top"
                        />&nbsp;PMChatBlog
                      </Navbar.Brand>
                      &nbsp;&nbsp;
                      <Navbar.Toggle aria-controls="basic-navbar-nav"></Navbar.Toggle>
                      <Navbar.Collapse id="basic-navbar-nav">
                          <Nav className="mr-auto">
                              <Nav.Link href="/">Home</Nav.Link>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <NavDropdown title="Blogs">
                                  <NavDropdown.Item href="/bestBlogs">Best Blogs</NavDropdown.Item>
                                  <NavDropdown.Item href="/blogs">All Blogs</NavDropdown.Item>
                              </NavDropdown>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <NavDropdown title="Templates">
                                  <NavDropdown.Item href="/bestTemplates">Best Templates</NavDropdown.Item>
                                  <NavDropdown.Item href="/templates">All Templates</NavDropdown.Item>
                              </NavDropdown>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <NavDropdown title="Videos">
                                  <NavDropdown.Item href="/bestVideos">Best Videos</NavDropdown.Item>
                                  <NavDropdown.Item href="/videos">All Videos</NavDropdown.Item>
                              </NavDropdown>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <Nav.Link href="/bestUsers">Best Users</Nav.Link>
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <Nav.Link href="/contact-us">About Us</Nav.Link>
                          </Nav>
                          <Form inline>
                            <FormControl className="searchForm" type="text" placeholder="Search" size="sm"/>&nbsp;
                            <Button variant="outline-secondary" size="sm"><BsSearch/></Button>
                          </Form>
                          &nbsp;&nbsp;&nbsp;
                          {currentUser ? (
                              <div>
                                  <Nav className="mr-auto">
                                      <NavDropdown title={<span><FaUserCircle/>{" "}{currentUser.username}</span>}>
                                          <NavDropdown.Item href="/profile">My Profile&nbsp;&nbsp;</NavDropdown.Item>
                                          <NavDropdown.Item href="/myBlogs">My Blogs&nbsp;&nbsp;</NavDropdown.Item>
                                          <NavDropdown.Item href="/myTemplates">My Templates&nbsp;&nbsp;</NavDropdown.Item>
                                          <NavDropdown.Item href="/myVideos">My Videos&nbsp;&nbsp;</NavDropdown.Item>
                                          <NavDropdown.Item href="/blog/newBlog">New Blog&nbsp;&nbsp;</NavDropdown.Item>
                                          <NavDropdown.Item href="/template/uploadTemplate">Upload Template&nbsp;&nbsp;</NavDropdown.Item>
                                          <NavDropdown.Item href="/video/uploadVideo">Upload Video&nbsp;&nbsp;</NavDropdown.Item>
                                          <NavDropdown.Divider />
                                          <NavDropdown.Item href="/login" onClick={this.logOut}>Log Out&nbsp;&nbsp;</NavDropdown.Item>
                                      </NavDropdown>
                                      &nbsp;&nbsp;&nbsp;&nbsp;
                                  </Nav>
                              </div>
                          ) : (
                            <div>
                                <Nav className="mr-auto">
                                    <Nav.Link href="/login">Log In</Nav.Link>
                                    <Nav.Link href="/signup">Sign Up</Nav.Link>
                                </Nav>
                            </div>
                          )}
                      </Navbar.Collapse>
                    </Navbar>
                    <Switch>
                      <Route exact path={["/", "/home"]} component={Home} />

                      <Route exact path="/user/:id" component={User} />
                      <Route exact path="/login" component={Login} />
                      <Route exact path="/signup" component={Signup} />
                      <Route exact path="/profile" component={Profile} />
                      <Route exact path="/updateProfile" component={UpdateProfile} />
                      <Route exact path="/changePassword" component={ChangePassword} />
                      <Route exact path="/bestUsers" component={BestUsers} />

                      <Route exact path="/blog/newBlog" component={CreateBlog} />
                      <Route exact path="/blog/:id" component={Blog} />
                      <Route exact path="/updateBlog/:id" component={UpdateBlog} />
                      <Route exact path="/blogs" component={Blogs} />
                      <Route exact path="/bestBlogs" component={BestBlogs} />
                      <Route exact path="/myBlogs" component={MyBlogs} />

                      <Route exact path="/template/uploadTemplate" component={UploadTemplate} />
                      <Route exact path="/template/:id" component={Template} />
                      <Route exact path="/updateTemplate/:id" component={UpdateTemplate} />
                      <Route exact path="/templates" component={Templates} />
                      <Route exact path="/bestTemplates" component={BestTemplates} />
                      <Route exact path="/myTemplates" component={MyTemplates} />

                      <Route exact path="/video/uploadVideo" component={UploadVideo} />
                      <Route exact path="/video/:id" component={Video} />
                      <Route exact path="/updateVideo/:id" component={UpdateVideo} />
                      <Route exact path="/videos" component={Videos} />
                      <Route exact path="/bestVideos" component={BestVideos} />
                      <Route exact path="/myVideos" component={MyVideos} />

                      <Route exact path="/error" component={ErrorPage} />
                    </Switch>
                </Router>
            </div>
            <Widget
              handleNewUserMessage={handleNewMessage}
              profileAvatar={logo}
              title="PMChatBot"
              subtitle="Welcome! How can I help you?"
            />
        </div>
    );
  }
}

export default App;
