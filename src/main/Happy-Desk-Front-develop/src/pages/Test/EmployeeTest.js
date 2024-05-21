import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../css/Test/EmployeeTest.css';
// import kakaoResultImage from '../../images/kakaoResultImage.png';



function EmployeeTest() {
    const navigate = useNavigate();
    const [page, setPage] = useState(0);
    const [mbti, setMbit] = useState(null);
  
    const [typeList, setTypeList] = useState([
        { name: 'I', count: 0 }, { name: 'E', count: 0 }, { name: 'S', count: 0 }, { name: 'N', count: 0 },
        { name: 'F', count: 0 }, { name: 'T', count: 0 }, { name: 'P', count: 0 }, { name: 'J', count: 0 },
    ])


    useEffect(()=>{
        console.log("mbti:", mbti);
        if (mbti) {
            navigate(`/result/${mbti}`);
        }
    },[mbti])

 
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

    const questionList = [
        {
            q: ['업무로 인해 스트레스를 잔뜩 받은 당신, 스트레스를 풀려고 한다. 당신의 선택은?'],
            a:
                [{ type: 'I', text: '넷플릭스와 맥주 한 캔, 혼자만의 힐링 시간을 가진다.' },
                { type: 'E', text: '친구를 만나서 하소연 타임을 가진다.' }]
        },
        {
            q: ['둘 중 더 가고 싶은 프로젝트는?'],
            a:
                [{ type: 'I', text: '점심시간 및 회식 때 정적이 흐르고, 일 하다가 스몰 토크하면 조용히 해달라며 매번 눈치주는 사람이 있는 프로젝트 ' },
                { type: 'E', text: '점심시간에 온갖 tmi들을 말하고 답하며, 사이가 좋아서 1달에 1번 주말에도 만나는 프로젝트' }]
        },
        {
            q: ['신입이 들어왔다. 나의 반응은?'],
            a:
                [{ type: 'I', text: '벌써 신입이 들어왔네. 말 걸어보고 싶지만, 다음에 기회가 있겠지 하며 넘긴다.' },
                { type: 'E', text: '신입이라 많이 낯설겠군. 말 좀 걸어볼까?하며 말을 건다.' }]
        },
        {
            q: ['할 일을 끝내고 나서 시계를 보니 오후 9시. 사무실에 혼자 있다는 걸 인지했을 때 나는'],
            a:
                [{ type: 'S', text: '어? 혼자 밖에 없네. 빨리 집에 가야지~라는 생각만 하며 퇴근한다.' },
                { type: 'N', text: '혼자 사무실에 있는데.... 상상의 나래를 펼치며 집에 간다.' }]
        },
        {
            q: ['눈 여겨 보던 프로젝트에 들어갈 수 있는 기회가 생겼다. 지원자 5명 중 1명만 뽑는다고 한다.'],
            a:
                [{ type: 'S', text: '어떻게 하면 내가 뽑힐 수 있을까?만 생각한다' },
                { type: 'N', text: '이미 프로젝트 팀원이 된 나를 상상한 후 어떻게 하면 내가 뽑힐 수 있을까?에 대한 생각을 한다' }]
        },
        {
            q: ['동료가 로또를 사왔다. 로또에 대해 이야기할 때 나는?'],
            a:
                [{ type: 'S', text: '되면 좋겠다. 제2의 인생 시작!' },
                { type: 'N', text: '정장입고 직원인 척 돈을 찾으러 다녀온 후 일단 집 부터 사고, 동료들한테 한 턱쏘고 퇴사한 다음... 로또 당첨된 나에 세밀하게 생각한다' }]
        },
        {
            q: ['퇴근하고 쉬고 있는데 상사에게 카톡이 왔다. 00님 죄송한데, 확인 부탁 드려요.라는 연락을 받고 마지못해서 확인을 해주었다. 그때 당신이 확인하면서 한 생각은?'],
            a:
                [{ type: 'F', text: '귀찮고 쉬고 싶지만, 야근하는 상대가 더 힘들겠다고 생각한다' },
                { type: 'T', text: '퇴근한 거 알면서 왜 연락하는 거야 라고 생각한다.' }]
        },
        {
            q: ['상사께서 여행을 다녀온 후 팀원들 생각나서 사왔다며 크고 무거운 상자를 꺼낸다'],
            a:
                [{ type: 'F', text: '우리를 위해 저 무거운 걸 사오셨다고?? 너무 감사하고 감동이야ㅜㅠ ' },
                { type: 'T', text: '저 무거운 걸 들고 오셨다니... 대체 뭘까?? 궁금하다. 기대되는 선물이군!' }]
        },
        {
            q: ['더 듣기 좋은 칭찬은?'],
            a:
                [{ type: 'T', text: 'oo씨가 똑똑해서 이번 것도 믿고 맡길 수 있어' },
                {
                    type: 'F', text: 'oo씨 노력하는 모습 보니까 믿고 맡길 수 있어'
                }]
        },

        {
            q: ['회사 워크샵 가기 2일 전 나는'],
            a:
                [{ type: 'P', text: '음~ 재밌겠다. 수영복도 챙기고~ 고데기도 챙기고~ 아 맞다! 질문 카드도 챙겨야겠어! 생각만 한다' },
                { type: 'J', text: '이미 캐리어에 짐을 다 싸두고, 더 챙겨야할 건 없겠지? 2중 체크 중' }]
        },
        {
            q: ['출근하고 자리에 앉은 나는'],
            a:
                [{ type: 'P', text: '하고 싶은 일부터 시작한다' },
                { type: 'J', text: '오늘할 업무 계획을 세운 후 일을 시작한다' }]
        },
        {
            q: ['너무 고생했으니까 고기를 사주신다고 한다.  나의 반응은?'],
            a:
                [{ type: 'P', text: '아싸~!!~! 고기 맛있겠당~!! 일정은 미루면 되지~!' },
                { type: 'J', text: '아... 오늘 일정 있는데... 맛있긴 하겠다...' }]
        },
        {
            q: ['테스트가 모두 끝났어요! 결과 보러 갑시다!'],
            a: [{ type: 'O', text: 'KCC 직장인 MBTI 결과 보러 가기' }]
        }
    ]

    const handleCkAnswer = (type, idx) => {
        setTypeList(typeList.map(item => {
            if (item.name === type) {
                return { ...item, count: item.count + 1 };
            } else {
                return item;
            }
        }));
        setPage(page + 1);

        if (idx + 1 === questionList.length) {
            setMbtiType();
            // setTimeout(() => {
            //     if (!hasClickedResultButton) {
            //         setIsResultBlurred(true);
            //     }
            // }, 3000);
        }
    };

    const setMbtiType = () => {
        let IorE =
            typeList.find(function (data) { return data.name === 'I' }).count >
            typeList.find(function (data) { return data.name === 'E' }).count ? 'I' : 'E'
        let SorN =
            typeList.find(function (data) { return data.name === 'S' }).count >
            typeList.find(function (data) { return data.name === 'N' }).count ? 'S' : 'N'
        let ForT =
            typeList.find(function (data) { return data.name === 'F' }).count >
            typeList.find(function (data) { return data.name === 'T' }).count ? 'F' : 'T'
        let PorJ =
            typeList.find(function (data) { return data.name === 'P' }).count >
            typeList.find(function (data) { return data.name === 'J' }).count ? 'P' : 'J'

        console.log(IorE);
        console.log(SorN);
        console.log(ForT);
        console.log(PorJ);
        const result = IorE + SorN + ForT + PorJ;
        console.log(result);
        setMbit(result);
    }


 

    return (
        <div className="EmployeeTest">
            <div className="testLayout">
                {page === 0 ?
                    <div className='startPageLayout'>
                        <div className='startLogo'>
                            <div>KCC TALK</div>
                            <div>▼</div>
                        </div>
                        <div onClick={() => setPage(1)} className='startButton'>직장인 유형 테스트 시작하기</div>
                    </div>
                    : page <= questionList.length ?
                        <div className='questionLayout'>
                            <div className='employeeTitle'>
                                <div>
                                    KCC 직장인 테스트</div>
                                <div>{`${page} / ${questionList.length}`}</div>
                            </div>
                            {questionList.map((val, idx) =>
                                <div className='questionList' style={{ display: page === idx + 1 ? 'flex' : 'none' }} key={idx}>
                                    <div className='questionItemLatyout'>
                                        <div className='profileImg'>
                                            <div /> <div />
                                        </div>
                                        <div className='chatListLayout'>
                                            {val.q.map((qval, qidx) =>
                                                <div key={qidx} className='chatBox'>
                                                    <div>◀</div> <div>{qval}</div>
                                                </div>
                                            )}
                                        </div>
                                    </div>
                                    <div className='answerItemLayout'>
                                        <div className='aChatBox'>
                                            <div>+</div> <div>#</div>
                                        </div>
                                        {val.a.map((aval, aidx) =>
                                            <div key={aidx} className='answerBox' onClick={() => handleCkAnswer(aval.type, idx)}>
                                                {aval.text}
                                            </div>
                                        )}
                                    </div>
                                </div>
                            )}
                        </div>
                        :  
                        <></>
                    }
            </div>
        </div>
    );
}



export default EmployeeTest;