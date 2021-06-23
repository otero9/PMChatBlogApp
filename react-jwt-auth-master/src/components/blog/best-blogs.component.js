import React, { Component } from "react";
import BlogService from "../../services/blog.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-css-only/css/bootstrap.min.css";
import { GiPodium } from "react-icons/gi";
import { BiBookAlt } from "react-icons/bi";


export default class BestBlogs extends Component {

    constructor(props) {
      super(props);
      this.renderViewBlog = this.renderViewBlog.bind(this);

      this.state = {
          blogs: {}
      };
    }

    renderViewBlog(blogId,title) {
      const urlViewBlog = "/blog/"+blogId;
      return <Link to={urlViewBlog}>{title}</Link>;
    }

    componentDidMount() {
      BlogService.getBestBlogs().then(
        response => {
          this.setState({
              blogs: {
                columns : [
                  { label: 'Points', field: 'points'},
                  { label: 'Title', field: 'title'},
                  { label: 'Category', field: 'category'},
                  { label: 'User', field: 'user'},
                  { label: 'Published', field: 'createdDate'}
                ],
                rows: (response.data!==undefined && response.data.length > 0 ? (response.data.map((blog, index) => (
                { points: blog.points, title: this.renderViewBlog(blog.blogId, blog.title),
                  category: blog.categoryName, user: blog.userName,
                  createdDate: blog.createdDate.substring(0, 10)
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

      return (
          <div className="container">
              <p/>
              <header className="jumbotron py-4 text-center">
                  <h1><GiPodium/></h1>
                  <h3><BiBookAlt/>&nbsp;Best Blogs</h3>
              </header>
              <MDBTable responsive>
                  <MDBDataTableV5
                    hover
                    striped
                    paging={false}
                    searching={false}
                    data={this.state.blogs}/>
              </MDBTable>
          </div>
      );
    }

}
