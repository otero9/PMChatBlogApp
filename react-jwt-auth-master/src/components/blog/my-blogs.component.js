import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import BlogService from "../../services/blog.service";
import UserService from "../../services/user.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-css-only/css/bootstrap.min.css";
import { BiBookAlt } from "react-icons/bi";

export default class MyBlogs extends Component {

  constructor(props) {
    super(props);
    this.renderViewBlog = this.renderViewBlog.bind(this);

    this.state = {
        redirect: null,
        userReady: false,
        currentUser: { username: "" },
        blogs: {}
    };
  }

  renderViewBlog(blogId,title) {
    const urlViewBlog = "/blog/"+blogId;
    return <Link to={urlViewBlog}>{title}</Link>;
  }

  componentWillMount() {
    const currentUser = UserService.getCurrentUser();
    if (!currentUser) this.setState({ redirect: "/login" });
    this.setState({ currentUser: currentUser, userReady: true });
  }

  componentDidMount() {
    BlogService.getBlogsByUserId(this.state.currentUser.id).then(
      response => {
        this.setState({
            blogs: {
              columns : [
                { label: 'Title', field: 'title'},
                { label: 'Published', field: 'createdDate'},
                { label: 'Category', field: 'category'},
                { label: 'Points', field: 'points'}
              ],
              rows: (response.data!==undefined && response.data.length > 0 ? (response.data.map((blog, index) => (
              { title: this.renderViewBlog(blog.blogId,blog.title),
                createdDate: blog.createdDate.substring(0, 10),
                category: blog.categoryName, points: blog.points
              }))) : [])
            }
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
  }

  render() {

    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser } = this.state;

    return (
        <div className="container">
            <p/>
            <header className="jumbotron py-4 text-center">
                <h3><BiBookAlt/>&nbsp;My blogs</h3>
            </header>
            {(currentUser) ? (
                <MDBTable responsive>
                    <MDBDataTableV5
                      hover
                      striped
                      order={['createdDate', 'desc']}
                      data={this.state.blogs}
                      entriesOptions={[20, 40, 60]}
                      entries={20}
                      searchTop
                      searchBottom={false}/>
                </MDBTable>
            ): (<Redirect to="/login"/>)}
        </div>
    );
  }

}
