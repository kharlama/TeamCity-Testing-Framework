package com.example.teamcity.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.elements.ProjectElement;
import com.example.teamcity.ui.elements.SideBarProjectElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ProjectsPage extends BasePage {
    private static final String PROJECTS_URL = "/favorite/projects";

    private ElementsCollection projectElements = $$("div[class*='Subproject__container']");

    private SelenideElement spanFavoriteProjects = $("span[class='ProjectPageHeader__title--ih']");

    private SelenideElement header = $(".MainPanel__router--gF > div");

    private SelenideElement searchProjectInput = $("#search-projects");
    private SelenideElement addToFavorites = $("[aria-label='Add to favorites...']");
    private SelenideElement allProjects = $("[span='All Projects']");
    private ElementsCollection sideBarProjectElements = $$("[data-test='sidebar-item']");

    // ElementCollection -> List<ProjectElement>
    // UI elements -> List<Object>
    // ElementCollection -> List<BasePageElement>

    public ProjectsPage() {
        header.shouldBe(Condition.visible, BASE_WAITING);
    }

    public static ProjectsPage open() {
        return Selenide.open(PROJECTS_URL, ProjectsPage.class);
    }

    public List<ProjectElement> getProjects() {
        return generatePageElements(projectElements, ProjectElement::new);
    }

    public ProjectsPage searchForProject(String projectName) {
        searchProjectInput.val(projectName);
        header.shouldBe(Condition.visible, BASE_WAITING);
        addToFavorites.shouldNotBe(Condition.visible, BASE_WAITING);
        allProjects.shouldNotBe(Condition.visible, BASE_WAITING);
        return this;
    }

    public List<SideBarProjectElement> getSideBarProjects() throws InterruptedException {
        Thread.sleep(1000);
        return generatePageElements( sideBarProjectElements, SideBarProjectElement::new);
    }
 }
