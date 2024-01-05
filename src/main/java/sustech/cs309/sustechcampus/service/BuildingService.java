package sustech.cs309.sustechcampus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sustech.cs309.sustechcampus.model.Building;
import sustech.cs309.sustechcampus.model.Comment;
import sustech.cs309.sustechcampus.repository.BuildingRepository;

@Service
public class BuildingService {

  private final BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
    initBuilding();
  }

  public Optional<Building> getBuildingByName(String buildingName) {
    Optional<Building> result = Optional.empty();
    List<Building> buildingByName = this.buildingRepository.findAllByBuildingName(buildingName);
    if (buildingByName.size() == 1) {
      result = Optional.of(buildingByName.get(0));
    }
    return result;
  }

  public List<Building> getBuildingByGroup(String buildingGroup) {
    return this.buildingRepository.findAllByBuildingGroup(buildingGroup);
  }

  public List<String> getAllGroup() {
    return this.buildingRepository.findAll().stream().map(Building::getBuildingGroup).distinct()
      .toList();
  }

  public Optional<UUID> addBuilding(Building building) {
    Optional<UUID> result = Optional.empty();
    if (this.getBuildingByName(building.getBuildingName()).isEmpty()) {
      this.buildingRepository.saveAll(List.of(building));
      result.of(building.getBid());
    }
    return result;
  }

  public void addCommentToBuilding(Building building, Comment comment) {
    if (building.getFirstComment() == null) {
      building.setFirstComment(comment.getCid());
    }
    building.setLastComment(comment.getCid());
    this.buildingRepository.save(building);
  }

  public void editBuildingByName(String oldName,String name, String introduction, String group) {
    Building building = this.getBuildingByName(oldName).get();
    building.setBuildingName(name);
    building.setDetailedInfo(introduction);
    building.setBuildingGroup(group);
    this.buildingRepository.save(building);
  }

  public List<Building> getAllBuilding() {
    return this.buildingRepository.findAll();
  }

  public void initBuilding() {
    List<Building> buildingList = new ArrayList<>();
    /*餐厅超市*/
    buildingList.add(new Building("湖畔餐厅", "湖畔餐厅的简单介绍",
      "湖畔餐厅以其绝佳的湖景和舒适的就餐环境闻名于校园。这里提供多样化的美食选择，包括地方特色菜肴和国际美食，满足不同口味和饮食需求。餐厅内部装潢典雅，设有宽敞的用餐区和户外座位，让学生在享受美食的同时也能感受大自然的美景。此外，湖畔餐厅还经常举办主题美食节和文化交流活动，为校园生活增添了丰富的文化元素。",
      "餐厅超市","/images/building/湖畔餐厅.png",114.005225,22.605807));
    buildingList.add(new Building("第二学生食堂", "第二学生食堂的简单介绍",
      "第二学生食堂是一个集多种餐饮风格于一体的餐厅，位于学生宿舍区附近，方便学生就餐。餐厅提供多样的菜式，从传统的中式菜肴到西式快餐，满足学生的不同饮食喜好。餐厅的设计现代而温馨，提供了一个舒适的就餐环境。此外，餐厅还定期推出季节性特色菜单和美食促销活动，丰富学生的餐饮体验。九栋十栋餐厅不仅是学生用餐的场所，也是他们社交互动和休闲放松的好地方。",
      "餐厅超市","/images/building/第二学生食堂.png",114.007511,22.609002));
    buildingList.add(new Building("荔园餐厅", "荔园餐厅的简单介绍",
      "荔园餐厅是一个集中式餐饮场所，提供多元化的餐饮服务。餐厅内部装潢温馨舒适，营造了一个宜人的就餐氛围。这里提供多种不同风格的菜肴，包括中式、西式和快餐等，满足不同顾客的口味需求。荔园餐厅不仅注重食物的味道和质量，还致力于提供健康的饮食选择。餐厅的位置便利，是校园内学生和教职工经常光顾的地方。",
      "餐厅超市","/images/building/荔园餐厅.png",114.006199,22.611116));
    buildingList.add(new Building("中心餐厅", "中心餐厅的简单介绍",
      "中心餐厅位于校园的核心地带，是学生和教职工最常光顾的就餐地点之一。餐厅提供广泛的餐饮选择，包括快餐、传统菜肴和健康轻食，确保每位顾客都能找到符合其口味的菜品。餐厅内部环境宽敞明亮，布局合理，设有多个不同区域，包括快速取餐区和休闲用餐区，以适应不同顾客的需求。中心餐厅不仅强调美食的口味和质量，还注重食品的营养均衡，致力于为校园社区提供健康的饮食选择。",
      "餐厅超市","/images/building/餐厅-中心餐厅.png",114.00438,22.603046));

    /*科研楼区*/
    buildingList.add(new Building("理学院", "理学院的简单介绍",
      "理学院西楼是一个专门为理学研究和教学设计的现代化建筑。它配备了高级实验室、研究设施和讲座厅，为理科学生和教师提供了优越的学习和研究环境。楼内的设计旨在促进学术交流，设有多个交流空间和休息区。此外，理学院西楼还定期举办学术研讨会和公开讲座，推动学术界的交流与合作。",
      "科研楼区","/images/building/理学院.png",114.005786,22.600173));
    buildingList.add(new Building("商学院", "商学院的简单介绍",
      "商学院楼是一个现代化的教学和研究建筑，专为商业和管理学科的学习设计。它拥有多功能教室、案例研讨室和电脑实验室，提供了一个适合商业教育的高科技环境。楼内还设有学生休息区和咖啡厅，为商学院的学生和教职工提供了休闲和交流的空间。",
      "科研楼区","/images/building/商血渊.png",114.006532,22.601228));
    /*buildingList.add(new Building("工学院北", "",
      "工学院北楼是工程学科的主要教学和研究中心。这座楼宇集中了一系列先进的工程实验室和研究设施，支持从基础工程到高端技术研究的广泛领域。楼内的设计注重实用性和创新性，为工程学学生提供了一个理想的学习和研究环境。",
      "科研楼区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%B7%A5%E5%AD%A6%E9%99%A22.png"));
    buildingList.add(new Building("工学院南", "",
      "工学院南楼是另一座专门用于工程教育和研究的建筑。它配备了现代化的教室和实验室，适用于各种工程项目和研究活动。楼内还设有多功能会议室和展览区，经常举办学术研讨会和技术展示。",
      "科研楼区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%AD%A6%E9%99%A2%E7%A7%91%E7%A0%94%E6%A5%BC/%E5%B7%A5%E5%AD%A6%E9%99%A2.png"));
    buildingList.add(new Building("人文社科学院", "",
      "人文社科学院楼是一个集教学、研究和文化活动于一体的综合性建筑。这里不仅设有舒适的讲座厅和教室，还拥有图书馆、研究中心和展览空间。楼内的环境优雅，充满了艺术氛围，为人文社科学者提供了一个理想的学术交流和创意表达的平台。",
      "科研楼区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%AD%A6%E9%99%A2%E7%A7%91%E7%A0%94%E6%A5%BC/%E4%BA%BA%E6%96%87%E7%A4%BE%E7%A7%91%E5%AD%A6%E9%99%A2.png"));

    /*宿舍楼区
    buildingList.add(new Building("学生宿舍", "",
      "学生宿舍2.1-2.17是由一系列楼宇组成的宿舍区，旨在为学生提供安全、舒适和便利的居住环境。每栋宿舍楼都设有完善的安全系统和生活设施，包括洗衣房、学习室和休闲区。宿舍内部设计简洁实用，每间房都有足够的存储空间和学习区域，满足学生的日常需求。此外，宿舍区还设有多功能活动室和绿色空间，鼓励学生参与社区活动和户外休闲，促进健康的生活方式和社交交往。",
      "宿舍楼区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%AE%BF%E8%88%8D/%E5%AD%A6%E7%94%9F%E5%AE%BF%E8%88%8D.png"));
    buildingList.add(new Building("教师公寓", "",
      "教师公寓提供了一个安静且舒适的居住环境，专门为教职工设计。这些现代化的公寓配备了全套家具、高效的家用电器和宽敞的生活空间，确保了教职工的生活便利和舒适。公寓区域内设有绿化区域和休闲设施，为教职工提供了一个放松身心的理想场所。此外，教师公寓的位置便利，靠近学校的教学区和研究设施，方便教职工的日常工作和交流。",
      "宿舍楼区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%AE%BF%E8%88%8D/%E6%95%99%E5%B8%88%E5%85%AC%E5%AF%93.png"));
    buildingList.add(new Building("九华精舍", "",
      "九华精舍是一处专为追求高质量生活的学生设计的宿舍区。每间宿舍都配备了现代化的家具和设施，包括高速网络连接和空调系统，确保了居住的舒适性和便利性。此外，九华精舍的设计注重环境的和谐与美观，宿舍区内绿树环抱，景观优美，为学生提供了一个宁静的学习和休息环境。它不仅是学生居住的地方，更是一个促进学习和个人发展的社区。",
      "宿舍楼区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%AE%BF%E8%88%8D/%E4%B9%9D%E5%8D%8E%E7%B2%BE%E8%88%8D.png"));

    /*体育场地
    buildingList.add(new Building("风雨操场", "",
      "风雨操场是一座全天候的室内运动场所，可供学生在各种天气条件下进行体育活动。该设施配备了多功能的运动场地，适合篮球、羽毛球、乒乓球等多种体育项目。风雨操场不仅是学生日常锻炼的场所，也经常用于举办学校的体育赛事和文化活动。",
      "体育场地","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E4%BD%93%E8%82%B2%E9%A6%86/%E9%A3%8E%E9%9B%A8%E6%93%8D%E5%9C%BA.png"));
    buildingList.add(new Building("松禾田径场", "",
      "松禾田径场是校园内的主要户外体育设施之一，拥有标准的田径跑道和多功能运动场地。这里不仅适合进行田径训练和比赛，还能举办足球、篮球等运动项目。田径场的开放设计使其成为学生们进行日常锻炼和体育活动的理想场所。",
      "体育场地","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E4%BD%93%E8%82%B2%E9%A6%86/%E6%9D%BE%E7%A6%BE%E4%BD%93%E8%82%B2%E5%9C%BA.png"));
    buildingList.add(new Building("润扬体育馆", "",
      "润扬体育馆是一个现代化的综合体育设施，内设有篮球馆、羽毛球馆和健身房等多种体育设施。体育馆的设计注重舒适性和多功能性，能够满足不同体育项目的需求。此外，体育馆还设有观众席，经常举办各类体育比赛和大型活动。",
      "体育场地","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E4%BD%93%E8%82%B2%E9%A6%86/%E6%B6%A6%E6%89%AC.png"));
    buildingList.add(new Building("棒球场", "",
      "棒球场是校园内专门为棒球运动设计的设施，配备了标准的棒球场地和观众席。场地的维护良好，为棒球爱好者提供了一个专业的训练和比赛环境。棒球场不仅是学校棒球队的主场，也对所有对棒球感兴趣的学生开放。",
      "体育场地","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E4%BD%93%E8%82%B2%E9%A6%86/%E6%A3%92%E7%90%83%E5%9C%BA.png"));
    buildingList.add(new Building("篮球场", "",
      "篮球场是一个室外的篮球运动场地，设有多个篮球架和充足的场地空间，适合进行篮球训练和比赛。场地周围环境优美，是学生们进行体育锻炼和休闲活动的热门场所。篮球场常年对学生和教职工开放，是校园体育文化的重要组成部分。",
      "体育场地","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E4%BD%93%E8%82%B2%E9%A6%86/%E7%AF%AE%E7%90%83%E5%9C%BA.png"));
    buildingList.add(new Building("足球场", "",
      "足球场是一块专业的草地足球场，设有完整的足球场地标记和守门员区。足球场经常用于学校的足球比赛和训练，同时也是学生进行足球活动的主要场所。足球场的设计考虑到了运动员的安全和舒适性，确保了比赛和训练的质量。",
      "体育场地","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E4%BD%93%E8%82%B2%E9%A6%86/%E8%B6%B3%E7%90%83%E5%9C%BA.png"));

    /*图书馆区
    buildingList.add(new Building("一丹图书馆", "",
      "一丹图书馆是校园内的主要图书馆之一，以其丰富的藏书和先进的学习资源而著称。图书馆提供了宽敞的阅读区域、个人学习空间和小组讨论室。除了纸质图书和期刊，图书馆还提供电子书籍和在线数据库，方便学生和教师进行学术研究。",
      "图书馆区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%9B%BE%E4%B9%A6%E9%A6%86/%E4%B8%80%E4%B8%B9.png"));
    buildingList.add(new Building("琳恩图书馆", "",
      "琳恩图书馆是一个现代化的学习中心，提供了一个安静和舒适的学习环境。图书馆内部设计优雅，设有多个阅读区域和研究空间，适合进行长时间的学习和研究。图书馆还提供多媒体学习资源和计算机设施，满足不同学科需求。",
      "图书馆区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%9B%BE%E4%B9%A6%E9%A6%86/%E6%B6%B5%E6%B3%B3%E5%9B%BE%E4%B9%A6%E9%A6%86.png"));
    buildingList.add(new Building("涵泳图书馆", "",
      "涵泳图书馆专注于提供人文社科类的学术资源。图书馆内环境宁静，拥有丰富的图书收藏和学术期刊，是研究人文和社会科学领域的理想场所。图书馆还定期举办讲座和展览，促进文化和学术交流。",
      "图书馆区","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%9B%BE%E4%B9%A6%E9%A6%86/%E7%90%B3%E6%81%A9.png"));

    /*教学楼区
    buildingList.add(new Building("第一教学楼", "",
      "一教是校园内的核心教学设施，拥有现代化的教室和讲堂。这座建筑以其先进的教学技术和灵活的空间布局著称，为各类学术课程和活动提供了理想的环境。教室内配备了高科技的学习工具，如智能黑板和多媒体系统，支持互动式学习。一教不仅是日常授课的场所，也经常举办讲座和研讨会，促进师生间的交流和思想碰撞。",
      "教学楼区","/images/building/第一教学楼.png"));
    buildingList.add(new Building("第二教学楼", "",
      "二教以其现代化的设计和多功能空间而著称，是一个促进创新和合作的理想场所。建筑内部拥有多个大小不一的教室，适合不同规模和形式的教学活动。每个教室都配备了最新的教育技术，包括高分辨率投影仪和高速网络连接，确保了教学和学习的高效性。此外，二教还设有学生休息区和小组讨论区，为学生提供了舒适的学习和交流环境。",
      "教学楼区","/images/building/第二教学楼.png"));
    buildingList.add(new Building("第三教学楼", "",
      "三教是一座融合传统与现代设计元素的教学楼，提供了宽敞舒适的学习空间。它的每个角落都经过精心设计，以满足学术需求。教室内部装饰简洁，配备了全面的教学设施，包括音频系统和视频投影，确保了教学质量。三教不仅是课程学习的场所，也是师生互动和知识分享的重要平台。此外，它还经常作为各种学术和文化活动的举办地，为校园生活增添了丰富多彩的内容。",
      "教学楼区","/images/building/第三教学楼.png"));

    /*其他建筑
    buildingList.add(new Building("行政楼", "",
      "行政楼是校园内的主要行政和管理中心，集中了学校的各个行政部门和办公室。这座建筑的设计旨在提供高效和舒适的工作环境，方便教职员工处理日常行政事务。楼内还设有会议室和接待区，用于举办学校的重要会议和接待访客。",
      "其他建筑","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%85%B6%E4%BB%96/%E8%A1%8C%E6%94%BF%E6%A5%BC.png"));
    buildingList.add(new Building("检测中心", "",
      "检测中心是校园内专门用于科学实验和技术检测的设施。中心配备了一系列先进的实验设备和分析工具，支持各类科学研究和技术创新。检测中心不仅为校内的科研项目提供服务，也对外提供技术支持和咨询。",
      "其他建筑","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%85%B6%E4%BB%96/%E6%A3%80%E6%B5%8B%E4%B8%AD%E5%BF%83.png"));
    buildingList.add(new Building("会议中心", "",
      "会议中心是一个多功能的会议和活动空间，适合举办各种规模的会议、研讨会和文化活动。中心的设计灵活多变，可以根据不同活动的需求调整空间布局。会议中心配备了现代化的音响和视听设备，确保活动的顺利进行。",
      "其他建筑","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%85%B6%E4%BB%96/%E4%BC%9A%E8%AE%AE%E4%B8%AD%E5%BF%83.png"));
    buildingList.add(new Building("办公楼", "",
      "办公楼是校园内多个学术部门和研究团队的工作场所。这些建筑提供了舒适的办公环境和必要的办公设施，支持教职员工的日常工作和学术研究。办公楼内部设计合理，旨在促进工作效率和团队合作。",
      "其他建筑","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%85%B6%E4%BB%96/%E5%8A%9E%E5%85%AC%E6%A5%BC.png"));
    buildingList.add(new Building("荔园", "",
      "荔园是校园内的一个景观园林区域，提供了一个宁静和优美的休闲空间。园内种植了各种植物和花卉，设有步行小径和休息区。荔园不仅是学生和教职员工休息和放松的好地方，也是校园的一个生态和文化景点。",
      "其他建筑","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%85%B6%E4%BB%96/%E8%8D%94%E5%9B%AD.png"));
    buildingList.add(new Building("慧园", "",
      "慧园是一个集学习、交流和创新于一体的综合性空间。园区内设有多个研究实验室、讲座厅和创新工作室，鼓励学生和教师进行跨学科的学术探索和创新实践。慧园的环境开放而富有创意，是校园内知识交流和创新活动的重要场所。",
      "其他建筑","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%85%B6%E4%BB%96/%E6%85%A7%E5%9B%AD.png"));
    buildingList.add(new Building("创园", "",
      "创园是校园内专门为创业和创新项目设计的区域。这里为学生和年轻创业者提供了办公空间、资源中心和辅导服务。创园的目标是激发创新思维，支持学生将创意转化为实际项目。园区内还定期举办创业讲座和工作坊，促进创业精神的培养和发展。",
      "其他建筑","https://raw.githubusercontent.com/aplacalike9050/CS309/main/%E5%85%B6%E4%BB%96/%E5%88%9B%E5%9B%AD.png"));
    buildingList.add(new Building("欣园", "",
      "欣园是校园内的另一个风景园林区，以其美丽的自然景观和宁静的氛围而闻名。园内有多个小湖和绿地，是学生和教职员工进行户外活动和放松的理想场所。欣园的设计旨在提供一个和谐的自然环境，促进校园社区成员的身心健康。",
      "其他建筑","/images/building/欣园.png"));*/

    this.buildingRepository.saveAll(buildingList);
  }
}
