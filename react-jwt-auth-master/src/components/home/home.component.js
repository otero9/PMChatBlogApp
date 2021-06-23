import React, { Component } from "react";
import CarouselHome from "./carousel-home.component";
import chatbot from "../../images/chatbot-icon2.png";
import template from "../../images/templates-icon.png";
import video from "../../images/video-icon.png";
import comment from "../../images/comments-icon.png";
import winners from "../../images/winners-icon.png";
import blog from "../../images/blog-icon.png";

export default class Home extends Component {

  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  render() {
    return (
      <div>
          <CarouselHome/>
          <div className="features-tiles-inner section-inner pt-0">
             <div className="section-header center-content div-header">
                <div className="container-xs">
                <br/>
                <h2 className="mt-0 mb-16">PMChatBlog</h2>
                   <p className="m-0">Welcome to our community!</p>
                   <p className="m-0">We would like to solve your doubts about Project Management!</p>
                </div>
             </div>
             <div className="tiles-wrap center-content">
                <div className="tiles-item reveal-from-bottom is-revealed">
                   <div className="tiles-item-inner">
                      <div className="features-tiles-item-header mb-16">
                          <img src={chatbot} width="64" height="64" alt=""/>
                      </div>
                      <div className="features-tiles-item-content">
                         <h4 className="mt-0 mb-8">ChatBot</h4>
                         <p className="m-0 text-sm">Ask your doubts.</p>
                      </div>
                   </div>
                </div>
                <div className="tiles-item reveal-from-bottom is-revealed">
                   <div className="tiles-item-inner">
                      <div className="features-tiles-item-header mb-16">
                          <img src={blog} width="64" height="64" alt=""/>
                      </div>
                      <div className="features-tiles-item-content">
                         <h4 className="mt-0 mb-8">Blogs</h4>
                         <p className="m-0 text-sm">Learn Project Management tips and techniques.</p>
                      </div>
                   </div>
                </div>
                <div className="tiles-item reveal-from-bottom is-revealed">
                   <div className="tiles-item-inner">
                      <div className="features-tiles-item-header mb-16">
                          <img src={comment} width="64" height="64" alt=""/>
                      </div>
                      <div className="features-tiles-item-content">
                         <h4 className="mt-0 mb-8">Comments</h4>
                         <p className="m-0 text-sm">Interact with the community.</p>
                      </div>
                   </div>
                </div>
                <div className="tiles-item reveal-from-bottom is-revealed">
                   <div className="tiles-item-inner">
                      <div className="features-tiles-item-header mb-16">
                              <img src={video} width="64" height="64" alt=""/>
                      </div>
                      <div className="features-tiles-item-content">
                         <h4 className="mt-0 mb-8">Videos</h4>
                         <p className="m-0 text-sm">Pearn the Project Management theory with the most didactic way.</p>
                      </div>
                   </div>
                </div>
                <div className="tiles-item reveal-from-bottom is-revealed">
                   <div className="tiles-item-inner">
                      <div className="features-tiles-item-header mb-16">
                            <img src={template} width="64" height="64" alt=""/>
                      </div>
                      <div className="features-tiles-item-content">
                         <h4 className="mt-0 mb-8">Templates</h4>
                         <p className="m-0 text-sm">Download templates to improve your Project Management.</p>
                      </div>
                   </div>
                </div>
                <div className="tiles-item reveal-from-bottom is-revealed">
                   <div className="tiles-item-inner">
                      <div className="features-tiles-item-header mb-16">
                            <img src={winners} width="64" height="64" alt=""/>
                      </div>
                      <div className="features-tiles-item-content">
                         <h4 className="mt-0 mb-8">Points</h4>
                         <p className="m-0 text-sm">Prove to be the best.</p>
                      </div>
                   </div>
                </div>
             </div>
          </div>
      </div>
    );
  }
}
