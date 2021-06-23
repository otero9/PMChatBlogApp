import scrapy
import json

class PMIBlogSpider(scrapy.Spider):

    count = 1
    name = 'pmiblogspider'
    start_urls = [
        'https://community.pmi.org/t5/the-official-pmi-blog/caring-for-family-caregivers-building-a-future-with-project/ba-p/301',
        'https://community.pmi.org/t5/the-official-pmi-blog/next-generation-capabilities-creating-a-new-performance-paradigm/ba-p/295',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-importance-of-regionalization-connecting-at-the-local-level/ba-p/290',
        'https://community.pmi.org/t5/the-official-pmi-blog/time-for-a-do-over-assessing-the-impact-of-the-covid-crisis-on/ba-p/282',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-unexpected-impact-of-covid-19-on-project-success/ba-p/278',
        'https://community.pmi.org/t5/the-official-pmi-blog/how-citizen-development-drives-business-agility/ba-p/272',
        'https://community.pmi.org/t5/the-official-pmi-blog/stepping-into-the-ring-what-do-boxing-and-project-management/ba-p/271',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-key-to-creating-clear-roadmaps-and-strategically/ba-p/268',
        'https://community.pmi.org/t5/the-official-pmi-blog/mastering-transformation-a-conversation-with-brightline-s/ba-p/256',
        'https://community.pmi.org/t5/the-official-pmi-blog/empowering-youth-as-changemakers/ba-p/253',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-how-can-i-manage-complex-ai-projects-part-two/ba-p/248',
        'https://community.pmi.org/t5/the-official-pmi-blog/celebrating-20-years-of-the-agile-manifesto/ba-p/242',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-how-can-i-recognize-excellence-in-project/ba-p/239',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-how-is-pmi-expanding-value-for-stakeholders/ba-p/235',
        'https://community.pmi.org/t5/the-official-pmi-blog/leveling-the-playing-field-women-in-project-management/ba-p/230',
        'https://community.pmi.org/t5/the-official-pmi-blog/building-agile-government-services/ba-p/223',
        'https://community.pmi.org/t5/the-official-pmi-blog/how-to-encourage-exponential-thinking/ba-p/220',
        'https://community.pmi.org/t5/the-official-pmi-blog/you-think-your-staff-wants-to-go-back-to-the-office-think-again/ba-p/210',
        'https://community.pmi.org/t5/the-official-pmi-blog/three-ways-citizen-development-will-jump-start-your-pm-career/ba-p/209',
        'https://community.pmi.org/t5/the-official-pmi-blog/how-can-i-manage-complex-ai-projects/ba-p/197',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-could-i-be-the-next-pmi-board-member/ba-p/194',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-truth-about-transformation/ba-p/191',
        'https://community.pmi.org/t5/the-official-pmi-blog/2021-megatrends-report-meeting-the-challenge-of-mounting/ba-p/187',
        'https://community.pmi.org/t5/the-official-pmi-blog/planting-the-seeds-of-citizen-developer-success/ba-p/181',
        'https://community.pmi.org/t5/the-official-pmi-blog/a-human-centered-approach-toward-business-transformation/ba-p/176',
        'https://community.pmi.org/t5/the-official-pmi-blog/why-gymnastic-organizations-are-ready-for-anything/ba-p/173',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-what-will-2021-hold-for-pmi/ba-p/167',
        'https://community.pmi.org/t5/the-official-pmi-blog/cultivating-competitive-advantage-with-early-adoption-of-citizen/ba-p/163',
        'https://community.pmi.org/t5/the-official-pmi-blog/five-world-changing-projects-to-watch-in-2021/ba-p/157',
        'https://community.pmi.org/t5/the-official-pmi-blog/agile-for-grown-ups/ba-p/154',
        'https://community.pmi.org/t5/the-official-pmi-blog/how-2020-has-built-resiliency-in-a-virtual-world/ba-p/151',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-nexus-of-digitization-and-democratization/ba-p/145',
        'https://community.pmi.org/t5/the-official-pmi-blog/beyond-vuca/ba-p/142',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-5g-project-revolution/ba-p/139',
        'https://community.pmi.org/t5/the-official-pmi-blog/bridging-the-governance-gap-how-to-safely-realize-the-potential/ba-p/131',
        'https://community.pmi.org/t5/the-official-pmi-blog/most-influential-projects-2020-thought-leaders-top-picks/ba-p/122',
        'https://community.pmi.org/t5/the-official-pmi-blog/tech-mahindra-ceo-says-quot-don-t-miss-out-on-citizen/ba-p/117',
        'https://community.pmi.org/t5/the-official-pmi-blog/most-influential-projects-2020-a-cause-for-optimism-in-tough/ba-p/111',
        'https://community.pmi.org/t5/the-official-pmi-blog/digital-transformation-and-implications-for-project-leaders/ba-p/110',
        'https://community.pmi.org/t5/the-official-pmi-blog/rebuilding-resilience-using-analytics/ba-p/104',
        'https://community.pmi.org/t5/the-official-pmi-blog/agile-myths-debunked/ba-p/95',
        'https://community.pmi.org/t5/the-official-pmi-blog/accelerating-into-the-future/ba-p/93',
        'https://community.pmi.org/t5/the-official-pmi-blog/more-than-tech-why-virtual-work-requires-trust-above-all/ba-p/86',
        'https://community.pmi.org/t5/the-official-pmi-blog/expert-advice-future-trends-in-managing-construction-projects/ba-p/85',
        'https://community.pmi.org/t5/the-official-pmi-blog/expert-advice-staying-true-to-your-mission-can-help-weather-a/ba-p/81',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-what-do-pmi-exams-prepare-you-for/ba-p/77',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-how-are-certification-exams-developed/ba-p/73',
        'https://community.pmi.org/t5/the-official-pmi-blog/are-you-allowing-your-teams-to-innovate/ba-p/70',
        'https://community.pmi.org/t5/the-official-pmi-blog/what-we-can-learn-from-a-new-generation-of-project-leaders/ba-p/67',
        'https://community.pmi.org/t5/the-official-pmi-blog/collaborative-leadership-the-power-skill-you-need-to-apply-now/ba-p/63',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-evolution-of-energy-in-the-middle-east-the-project-manager-s/ba-p/60',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-how-do-i-get-started-in-a-project-management/ba-p/56',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-importance-of-project-management-during-turbulent-times/ba-p/53',
        'https://community.pmi.org/t5/the-official-pmi-blog/how-organizations-are-navigating-disruptive-change/ba-p/49',
        'https://community.pmi.org/t5/the-official-pmi-blog/a-first-look-under-the-hood-of-the-pmbok-guide-seventh-edition/ba-p/46',
        'https://community.pmi.org/t5/the-official-pmi-blog/lessons-in-project-management-from-day-zero-in-south-africa/ba-p/43',
        'https://community.pmi.org/t5/the-official-pmi-blog/three-simple-ways-to-drive-covid-inspired-change/ba-p/40',
        'https://community.pmi.org/t5/the-official-pmi-blog/bring-on-the-cto-why-having-a-chief-transformation-officer-makes/ba-p/36',
        'https://community.pmi.org/t5/the-official-pmi-blog/how-to-optimize-your-wow-with-disciplined-agile/ba-p/33',
        'https://community.pmi.org/t5/the-official-pmi-blog/africa-rising-why-project-managers-are-critical-to-africa-s/ba-p/30',
        'https://community.pmi.org/t5/the-official-pmi-blog/ask-pmi-anything-coping-with-loneliness-in-a-remote-working/ba-p/26',
        'https://community.pmi.org/t5/the-official-pmi-blog/mastering-strategy-implementation-in-transformative-times/ba-p/22',
        'https://community.pmi.org/t5/the-official-pmi-blog/the-end-of-agile-no-the-end-of-undisciplined-agile/ba-p/18',
        'https://community.pmi.org/t5/the-official-pmi-blog/tips-to-find-the-perfect-talent-technology-balance/ba-p/15',
        'https://community.pmi.org/t5/the-official-pmi-blog/essential-tips-to-ace-your-next-videoconference/ba-p/12',
        'https://community.pmi.org/t5/the-official-pmi-blog/managing-risk-during-a-crisis-six-lessons/ba-p/8',
        'https://community.pmi.org/t5/the-official-pmi-blog/open-for-business/ba-p/4'
    ]

    def __init__(self) :
        self.fp=open("content.json",'w',encoding='utf-8')
        self.fp.write('{'+'\n')
        self.fp.write('"intents": ['+'\n')

    def parse(self, response):

        if (response.status == 200):
            self.fp.write('{'+'\n')
            self.fp.write('"tag": "'+str(self.count)+'",'+'\n')
            self.fp.write('"patterns":"'+response.css('h1 span::text').get().replace('"','\'')+'",'+'\n')
            responses = ''
            for quote in response.css('p::text'):
                ptag = quote.get()
                if (str(ptag) != '\xa0') & (str(ptag) != '©\xa02020\xa0Project Management Institute, Inc.'):
                    responses = responses + str(ptag.replace('"', '\''))
            responses_final = responses.replace('"', '\'')
            if not responses_final:
                for quote in response.css('p span::text'):
                    ptag = quote.get()
                    if (str(ptag) != '\xa0') & (str(ptag) != '©\xa02020\xa0Project Management Institute, Inc.'):
                        responses = responses + str(ptag.replace('"', '\''))
                responses_final = responses.replace('"', '\'')
            self.fp.write('"responses": ["'+str(responses_final)+'"],'+'\n')
            self.fp.write('"context_set": ""'+'\n')
            if (self.count == 41):
                self.fp.write('}'+'\n')
                self.fp.write(']'+'\n')
                self.fp.write('}'+'\n')
            else:
                self.fp.write('},'+'\n')
            self.count = self.count + 1
