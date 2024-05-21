import React, { useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import '../css/SimpleSlider.css';
import img1 from '../images/kcctest.png';
import hbd from '../images/생일_열정.png';
import retire from '../images/퇴임.png';
import img3 from '../images/news.png';

export default function SimpleSlider() {
    const navigate = useNavigate();
    const goTest = () => {
        navigate('/employeetest');
    }

  var settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 4000
  };


  return (
    <Slider {...settings}>
      <div>
        <img src={img1} className="slider__img" alt='테스트' onClick={goTest}/>
      </div>
      <div>
        <img src={hbd} className="slider__img" alt="생일"/>
      </div>
      <div>
         <img src={retire} className="slider__img" alt="이벤트"/>
      </div>
    </Slider>
  );
}