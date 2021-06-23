import React, { Component } from "react";
import "react-responsive-carousel/lib/styles/carousel.min.css"; // requires a loader
import { Carousel } from "react-responsive-carousel";
import image1 from "../../images/project-management2.jpg";
import image2 from "../../images/project-management3.jpg";
import image3 from "../../images/project-management4.png";
import image4 from "../../images/project-management5.jpg";
import image5 from "../../images/project-management6.jpeg";
import image6 from "../../images/project-management7.jpg";
import image7 from "../../images/project-management8.jpg";
import image8 from "../../images/project-management9.jpg";
import image9 from "../../images/project-management10.jpg";
import image10 from "../../images/project-management11.jpg";
import image11 from "../../images/project-management12.jpg";
import image12 from "../../images/project-management13.jpg";
import image13 from "../../images/project-management14.jpg";
import image14 from "../../images/project-management15.jpg";
import image15 from "../../images/project-management16.jpg";
import image16 from "../../images/project-management17.jpg";

export default class CarouselHome extends Component {
    render() {
        return (
            <div className="carousel-container">
                <Carousel autoPlay="true" stopOnHover="true" swipeable="false" interval="10000" transitionTime="5000" centerMode="false" showStatus="false" showThumbs="false" showIndicators="true" showArrows="true" infiniteLoop="true">
                    <div style={{height: "350px"}}>
                        <img src={image4} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image1} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image2} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image14} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image5} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image6} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image7} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image8} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image9} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image10} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image11} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image12} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image13} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image3} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image15} alt=""/>
                    </div>
                    <div style={{height: "350px"}}>
                        <img src={image16} alt=""/>
                    </div>
                </Carousel>
            </div>
        );
    }
}
