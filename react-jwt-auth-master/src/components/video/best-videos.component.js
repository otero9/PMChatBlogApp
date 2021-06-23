import React, { Component } from "react";
import VideoService from "../../services/video.service";
import { MDBDataTableV5 } from 'mdbreact';
import { MDBTable } from 'mdbreact';
import { Link } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import "bootstrap-css-only/css/bootstrap.min.css";
import { GiPodium } from "react-icons/gi";
import { BiVideo } from "react-icons/bi";


export default class BestVideos extends Component {

    constructor(props) {
      super(props);
      this.renderViewVideo = this.renderViewVideo.bind(this);

      this.state = {
          videos: {}
      };
    }

    renderViewVideo(videoId,videoTitle) {
      const urlViewVideo = "/video/"+videoId;
      return <Link to={urlViewVideo}>{videoTitle}</Link>;
    }

    componentDidMount() {
      VideoService.getBestVideos().then(
        response => {
          this.setState({
              videos: {
                columns : [
                  { label: 'Points', field: 'points'},
                  { label: 'Title', field: 'title'},
                  { label: 'Category', field: 'category'},
                  { label: 'User', field: 'user'},
                  { label: 'Published', field: 'createdDate'}
                ],
                rows: (response.data!==undefined && response.data.length > 0 ? (response.data.map((video, index) => (
                { points: video.points, title: this.renderViewVideo(video.videoId, video.videoTitle),
                  category: video.categoryName, user: video.userName,
                  createdDate: video.createdDate.substring(0, 10)
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
                  <h3><BiVideo/>&nbsp;&nbsp;Best Videos</h3>
              </header>
              <MDBTable responsive>
                  <MDBDataTableV5
                    hover
                    striped
                    paging={false}
                    searching={false}
                    data={this.state.videos}/>
              </MDBTable>
          </div>
      );
    }

}
