// ResultPage.js
import React, {useEffect, useState} from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import '../../css/Test/EmployeeTest.css';
import pageBanner1 from '../../images/24.3 포스터1.png';
import pageBanner2 from '../../images/24.3 포스터2.png';
import pageBanner3 from '../../images/24.3 포스터3.png';


function ResultPage() {
  const { mbtiType } = useParams();
  const navigate = useNavigate();
  const [mbtiContents, setMbtiContents] = useState({ mbti: '', info: '', contents: [] });
  const [isResultBlurred, setIsResultBlurred] = useState(true);


    useEffect(()=>{
        console.log("mbtiType:", mbtiType);
        setMbtiInformatin();
    },[])

   
    useEffect(() => {
        const script = document.createElement('script');
        script.src = "https://developers.kakao.com/sdk/js/kakao.js";
        script.onload = () => Kakao.init('c81b0e44811321a4f3630eed2d462942');
        document.head.appendChild(script);

        return () => {
            document.head.removeChild(script);
        };
    }, []);

    useEffect(() => {
        document.body.classList.add('hide-header-footer');
        setVh();
        window.addEventListener('resize', setVh);

        return () => {
            document.body.classList.remove('hide-header-footer');
            window.removeEventListener('resize', setVh);
        };
    }, []);

    const setVh = () => {
        const vh = window.innerHeight * 0.01;
        document.documentElement.style.setProperty('--vh', `${vh}px`)
    }



  /** 홈으로 가기 */
  const goHome = () => {
    navigate('/');
};
    const goTestAgain = () => {
        navigate('/employeetest')
    }

/** 쿠팡 광고 후 결과보기 */ 
const handleShowResultButton = () => {
    setIsResultBlurred(false);
    window.open('https://link.coupang.com/a/buP8ys', '_blank');
};

/** 카카오톡 공유 */
const shareKakao = () => {
    if (!window.Kakao.isInitialized()) {
        window.Kakao.init('c81b0e44811321a4f3630eed2d462942');
    }

    // MBTI 결과를 기반으로 URL 생성
    const resultType = "YOUR_MBTI_RESULT"; // 이 부분을 사용자의 MBTI 결과로 동적으로 설정
    // 사용자의 결과를 볼 수 있는 페이지 URL
    // const shareUrl = `${window.location.origin}/#/yourResultPage?result=${resultType}`; 
    const shareUrl = `${window.location.origin}/#/result/${mbtiType}`;
    console.log("shareUrl",shareUrl);
    window.Kakao.Link.sendDefault({
        objectType: 'feed',
        content: {
            title: 'KCC 직장인 MBTI 테스트',
            description: '당신의 직장인 유형 결과를 확인해보세요!',
            imageUrl: 'https://kcc.daouoffice.com/resources/images/mail/inline/20240322/jmsung@kcc.co.kr_attach_img_5F0A119022724B2EBCB1B82C2329508E.PNG',
            link: {
                mobileWebUrl: shareUrl,
                webUrl: shareUrl
            },
        },
        buttons: [
            {
                title: '결과 보기',
                link: {
                    mobileWebUrl: shareUrl,
                    webUrl: shareUrl
                },
            }
        ],
    });
}


function setMbtiInformatin() {
    let mc = [
        { mbti: 'ENTP', info: '발표왕 능력자', contents: ['"회의실에서 PT 마법을 부립니다. 슬라이드 하나로 모두를 홀릭!"', '"아이디어 생산기, 브레인스토밍이 필요할 땐 저를 호출하세요!"', '"독무대도 좋지만, 앙상블이 더 좋아요. 혼자도, 팀과도 찰떡궁합!"'] },
        { mbti: 'INTP', info: '아이디어 뱅크', contents: ['"문제가 있나요? 제 머릿속 아이디어 은행에서 출금해드릴게요!"', '"이론적 탐구는 제 취미, 복잡한 보고서도 술술 읽혀요!"', '"독립적인 프로젝트, 제게 주세요. 자유롭게 날아다니는 걸 좋아하니까요!"', '"이세은,서해수 사원과 같은 유형입니다."'] },
        { mbti: 'ESTJ', info: '팀의 믿음직한 리더', contents: ['"계획과 체계, 제 두 번째 이름입니다. 프로젝트는 제게 맡기세요!"', '"실행은 제게 맡겨주세요, 일 처리 속도에 놀라실 거예요!"', '"리더십은 타고나는 거죠. 팀을 하나로 모으는 건 제 취미랍니다!"', '"서영은 사원과 같은 유형입니다."'] },
        { mbti: 'ESTP', info: '액션파 실천가', contents: ['"문제 발생? 현장에서 바로 해결! 액션 영화 주인공처럼요."', '"적응력의 달인, 변화는 제게 두려움이 아니라 재미죠!"', '"팀 활동이라면 저 앞장! 에너지 넘치게 이끌어드려요."'] },
        { mbti: 'ISFJ', info: '따뜻한 보호자', contents: ['"팀원들의 세심한 조력자, 누군가 커피가 필요할 때 저는 이미 준비 중!"', '"안정적인 근무 환경의 마법사, 저의 책상은 항상 편안함의 상징!"', '"신뢰와 책임감, 제가 바로 그 신뢰할 수 있는 바위 같은 존재죠."', '"추다빈사원과 같은 유형입니다."'] },
        { mbti: 'ISTP', info: '유연한 해결사', contents: ['"프린터 고장? 컴퓨터 문제? 저에게 맡기세요, 손만 대도 고쳐져요!"', '"혼자 일하는 걸 선호해요. 조용한데, 생각보다 일이 빨리 끝나죠!"', '"상황에 따라 유연하게 대처해요. 변화는 저의 또 다른 이름이죠!"', '"이상욱, 배수연 사원과 같은 유형입니다."'] },
        { mbti: 'ENFJ', info: '영감을 주는 멘토', contents: ['"팀원들을 독려하는 것은 제 일, 모두에게 긍정적인 에너지를 뿜뿜!"', '"분위기 메이커와 멘토의 중간, 제가 바로 그 사람이죠!"', '"리더십과 카리스마로 팀을 이끕니다. 모두가 따르고 싶어하는 그런 존재죠!"', '"나현웅,이세인,이은지,최민혁 사원과 같은 유형입니다."'] },
        { mbti: 'INFJ', info: '비전을 제시하는 조언자', contents: ['"미래에 대한 통찰력으로 팀에 길을 제시해요. 저의 조언, 금값이죠!"', '"가치와 비전 공유를 통해 팀을 한 마음으로 만듭니다. 저는 바로 그 연결고리!"', '"이해관계 조율의 달인, 팀워크는 제게 맡기세요!"'] },
        { mbti: 'ENTJ', info: '전략적인 지휘자', contents: ['"전략과 계획으로 승리를 이끕니다. 전쟁에서도 이길 그런 전략이죠!"', '"효율과 생산성, 제 작업의 핵심. 시간은 금이니까요!"', '"강력한 리더십으로 팀을 목표까지 이끌죠. 따라오세요, 승리로 가는 길입니다!"'] },
        { mbti: 'INTJ', info: '혁신을 이끄는 전략가', contents: ['"혁신적인 아이디어로 미래를 그려요. 제 머릿속은 항상 미래로 가득 차 있죠!"', '"복잡한 문제도 저에겐 장기판. 하나씩 놓여 가며 해결해나가요!"', '"독립적인 성향 덕분에 제 일은 제가 가장 잘 합니다. 자율성, 그게 바로 키워드죠!"'] },
        { mbti: 'ENFP', info: '무한한 가능성의 탐험가', contents: ['"새로운 아이디어로 항상 팀에 활력을! 에너지 드링크보다 낫죠!"', '"유연한 사고로 항상 새로운 해결책을 제시해요. 박스 밖이 제 집이죠!"', '"팀워크를 중시해요. 함께라면 우리, 어떤 문제도 해결할 수 있어요!"', '"성지민사원과 같은 유형입니다."'] },
        { mbti: 'INFP', info: '깊은 성찰의 글로벌', contents: ['"공감과 이해로 팀원들의 마음을 따뜻하게 해요. 제 별명은 ‘팀의 담요’!"', '"창의적인 해결책을 제시해요. 상자 안의 생각은 이제 그만!"', '"일과 가치를 연결지어요. 제 일은 제 이야기와 같으니까요!"', '"정권영사원과 같은 유형입니다."'] },
        { mbti: 'ESFP', info: '에너지 넘치는 연예인', contents: ['"사무실 분위기 메이커, 제 에너지는 220V입니다!"', '"실용적인 해결책으로 일상을 더 즐겁게 만들어요. 일상에도 향신료가 필요하죠!"', '"팀워크를 활성화시키는 데 제가 전문가! 우리 팀은 항상 에너지가 넘쳐요!"'] },
        { mbti: 'ISFP', info: '섬세한 예술가', contents: ['"업무에 창의성을 더해요. 사무실도 제 캔버스죠!"', '"감성적인 접근으로 일을 합니다. 감성이 풍부해야 업무도 즐겁죠!"', '"조화로운 작업 환경을 선호해요. 제 책상 주변은 항상 평화로운 정원 같아요!"', '"최준원사원과 같은 유형입니다."'] },
        { mbti: 'ISTJ', info: '논리적인 관리자', contents: ['"세부 사항에 집중하는 것, 제 일의 비밀 무기죠. 누락된 컴마 하나도 찾아내요!"', '"규칙과 절차, 제 최고의 친구. 업무에 있어서는 제가 바로 규칙의 수호자!"', '"신뢰와 책임감, 저의 두 번째 이름. 업무는 언제나 제 시간에, 제대로!"'] },
        { mbti: 'ESFJ', info: '따뜻한 사회자', contents: ['"모든 일에 적극적으로 참여해 기분 좋은 분위기를 만들어요!"', '"조직의 필요를 정확히 파악하고, 그에 따른 해결책을 제시하여 팀의 성과를 극대화합니다!"', '"갈등 상황에서도 중재자 역할을 자처하며, 긍정적인 결과를 이끌어내는 데 능숙해요!"', '"양정열사원과 같은 유형입니다."'] }
    ]

    const foundMbtiContent = mc.find(val => val.mbti === mbtiType);
    if (foundMbtiContent) {
        setMbtiContents(foundMbtiContent);
    } else {
        console.log(`No content found for MBTI type: ${mbtiType}`);
    }
}

useEffect(()=>{
    console.log(mbtiContents);
},[mbtiContents])

const saveImage = (url, fileName) => {
    const link = document.createElement('a');
    link.href = url;
    link.download = fileName;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

  
  return (
    <div className="resultContainer">
        <div className="resultTop">
            <div className='questionLayout'>
                <div className='employeeTitle'>
                    <div>KCC 직장인 테스트</div>
                    <div className='title__group'>
                        <div onClick={goHome}>홈으로</div>
                        <div onClick={goTestAgain}>다시하기</div>
                    </div>
                </div>
                { mbtiContents? (
                        <div className='questionList' style={{ display: 'flex' }}>
                            <div className='questionItemLatyout'>
                                <div className='profileImg'>
                                    <div /><div />
                                </div>
                                <div className='chatListLayout'>
                                    <div className='chatBox'>
                                        <div>◀</div><div>당신의 직장 MBTI는 {mbtiContents.mbti} - {mbtiContents.info}입니다.</div>
                                    </div>
                                    <div className={isResultBlurred ? "resultBottomBlurred" : "resultBottom"}>
                                        <div className='chatBox'>
                                            <div>◀</div><div>{mbtiContents.mbti}유형은</div>
                                        </div>
                                        {mbtiContents.contents?.map((val, idx) => (
                                            <div className='chatBox' key={idx}>
                                                <div>◀</div><div>{val}</div>
                                            </div>
                                        )) || null}

                                    </div>
                                </div>
                            </div>

                    <div className="share-container">
                        <a id="kakaotalk-sharing-btn" className="share-link" onClick={shareKakao}>
                            <img src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png"
                                alt="카카오톡 공유 보내기 버튼" className="share-icon" />
                            <span className="share-text">카카오톡 공유하기</span>
                        </a>
                    </div>
                    <div className="banner-container">
                        <div className="banner-item">
                            <img src={pageBanner1} alt="권혁상 사장님 퇴임을 축하드립니다." className="page-banner" />
                            <button onClick={() => saveImage(pageBanner1, '권혁상 사장님 퇴임을 축하드립니다.png')} className="saveImageButton">이미지 저장하기</button>
                        </div>
                        <div className="banner-item">
                            <img src={pageBanner2} alt="정권영 사원의 생일을 축하합니다" className="page-banner" />
                            <button onClick={() => saveImage(pageBanner2, '정권영 사원의 생일을 축하합니다.png')} className="saveImageButton">이미지 저장하기</button>
                        </div>
                        <div className="banner-item">
                            <img src={pageBanner3} alt="양정열 사원의 생일을 축하합니다" className="page-banner" />
                            <button onClick={() => saveImage(pageBanner3, '양정열 사원의 생일을 축하합니다.png')} className="saveImageButton">이미지 저장하기</button>
                        </div>
                    </div>
                    </div>
                    ) : <></>
                }
             
            </div>
        </div>

    {isResultBlurred && (
        <div className="showResultButton">
            <button onClick={handleShowResultButton}>
                <span className="buttonTextTop">쿠팡 광고보고</span>
                <span className="buttonTextBottom">결과 보러가기</span>
            </button>
        </div>
    )}

    </div>
  );
}

export default ResultPage;
